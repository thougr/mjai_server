package com.example.bridge_server;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@CrossOrigin
public class BridgeServerApplication {
    static class StreamConsumer extends Thread {
        InputStream is;
        String type;

        StreamConsumer (InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        public void run () {
            try {
                InputStreamReader isr = new InputStreamReader (is);
                BufferedReader br = new BufferedReader (isr);
                String line = null;
                while ((line = br.readLine()) != null)
                    System.out.println (type + ">" + line);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    @PostMapping("/akochan")
    public String doAkochan(@RequestBody Map<String, Object> map) {
        // save actions to file
        System.out.println(JSON.toJSONString(map));
        System.out.println(map.get("actions"));
        // ArrayList to String []
        List actions = (List) map.get("actions");
        int seat = (int) map.get("seat");
        System.out.println(JSON.toJSONString(actions));
        System.out.println(seat);

        clearActionsFile();
        writeActionsToFile(actions);
        String[] args = new String[4];

        args[0] = "./system.exe";
        args[1] = "mjai_log";
        args[2] = "actions.json";
        args[3] = String.valueOf(seat);
        return execute(args);
    }

    public void clearActionsFile() {
        // write actions to file split with line
        try (PrintWriter writer = new PrintWriter("actions.json", "UTF-8")) {
            writer.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // write actions to file
    public void writeActionsToFile(List<Object> actions) {
        // write actions to file split with line
        try (PrintWriter writer = new PrintWriter("actions.json", "UTF-8")) {
            for (Object action : actions) {
                writer.println(JSON.toJSONString(action));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // hello world test
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
    public static void main(String[] args) {
        SpringApplication.run(BridgeServerApplication.class, args);
    }

    // copy system.exe , libai.so, setup_mjai.json, param
    // cors enable localhost
    //  send three args to execute  at /Users/hougrt/banzhuan/akochan/system.exe and return the result to the client
    public static String execute(String[] args) {
        String result = "";
        try {
            Process process = Runtime.getRuntime().exec(args);
            java.io.InputStream is = process.getInputStream();
            // use for debug
//            StreamConsumer errorGobbler = new StreamConsumer(process.getErrorStream(), "ERROR");
//            errorGobbler.start();
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            result = s.hasNext() ? s.next() : "";
            result = result.trim();
            // get last line of result
            result = result.substring(result.lastIndexOf("\n"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        // get the result from the system.exe
        System.out.println(result);
        return result;
    }


}
