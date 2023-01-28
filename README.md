# mjai_server

## 关于这个工程
该项目是运行[Akochan](https://github.com/critter-mj/akochan) AI的服务器项目。 

## 如何上手

* 在地址栏输入chrome://flags/#allow-insecure-localhost并启用，重启浏览器。
* git clone该项目，打开idea，右键pom.xml->maven->reload project, 还有安装java的方法请教网上。
* 点击BridgeServerApplication.java，运行main方法即可。
* 根据[akochan](https://github.com/critter-mj/akochan) 编译项目，遇到问题的话根据[mjai-reviewer](https://github.com/Equim-chan/mjai-reviewer)
中的Build-Engines-Akochan部分进行操作。
* 再将以下编译Akochan后的文件复制到本项目,然后就可以了：  
  ai.dll (windows)  
  libai.so (linux)  
  system.exe  
  setup_mjai.json  
  params  

## 存在的问题
* 代码有待整理