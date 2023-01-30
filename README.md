# mjai_server

## 关于这个工程
该项目是运行[Akochan](https://github.com/critter-mj/akochan) Mahjong AI的服务器项目。 

## 如何上手
方法一（推荐）:
* git clone git@github.com:thougr/mjai_server.git
* 下载docker，执行以下语句：  
  cd mjai_server  
  docker build -t mjai_server:v1 .  
  docker run -p 8787:8787 mjai_server:v1

方法二：
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

## 可能存在的问题
* window docker build时遇到Error response from daemon: open \\.\pipe\docker_engine_linux: The system cannot
  find the file specified.
解决办法: 在powershell输入并执行  
Enable-WindowsOptionalFeature -Online -FeatureName $(“Microsoft-Hyper-V”, “Containers”) -All

* docker git clone时出现超时，Empty Response等：  
解决办法: 给docker开代理
## 存在的问题
* 代码有待整理
