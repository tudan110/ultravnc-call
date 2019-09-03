# ultravnc-call
&ensp;&ensp;&ensp;&ensp;UltraVNC 调用程序，仅适用于 windows（因为 UltraVNC 只有 windows 版本）。基于 UltraVNC Repeater 搭建中继服务器，使用本程序辅助调用 UltraVNC Server 和 Viewer ，实现复杂网络环境里的远程桌面功能。

# 如何搭建中继服务器
&ensp;&ensp;&ensp;&ensp;请参考我的博文：
https://blog.csdn.net/WTUDAN/article/details/100214799

1. ### UltraVNCServerStarter（UltraVNCServer 启动器）

   参数讲解：

   1. 编号，唯一，必须是数字，且至少三位（即最小是100）

   2. winvnc.exe 路径（可选参数）

   3. 中继器服务器 IP（可选参数）

   4. 中继器 UltraVNC Server 监听端口（可选参数）

   ```
   # 中括号[]代表可选参数，若不填，则用配置文件中对应的默认值
   java -jar ultravnc-server-starter-1.0.jar 90699 ["C:/Program Files/uvnc bvba/UltraVNC/winvnc.exe"] [10.20.16.48] [5500]
   ```

2. ### UltraVNCServerStoper（UltraVNCServer 停止器）

   参数讲解：

   1. 进程名称（可选参数）

   ```
   # 中括号[]代表可选参数，若不填，则用配置文件中对应的默认值
   java -jar ultravnc-server-stoper-1.0.jar [winvnc.exe]
   ```

3. ### UltraVNCViewerStarter（UltraVNCViewer 启动器）

   参数讲解：

   1. 编号，唯一，必须是数字，且至少三位（即最小是100）

   2. vncviewer.exe 路径（可选参数）

   3. 中继器服务器 IP（可选参数）

   4. 中继器 UltraVNC Viewer 监听端口（可选参数）

   ```
   # 中括号[]代表可选参数，若不填，则用配置文件中对应的默认值
   java -jar ultravnc-viewer-starter-1.0.jar 90699 ["C:/Program Files/uvnc bvba/UltraVNC/vncviewer.exe"] [10.20.16.48] [5901]
   ```

4. ### UltraVNCIniModifier（修改 UltraVNC 配置文件 ultravnc.ini）

   &ensp;&ensp;&ensp;&ensp;要使 ultravnc.ini 生效，须安装系统服务，不建议这么做，使用前三个接口就可以搞定了。

   参数讲解：

   1. 编号，唯一，必须是数字，且至少三位（即最小是100）

   2. ultravnc.ini 配置文件路径（可选参数）
   
   ```
   # 中括号[]代表可选参数，若不填，则用配置文件中对应的默认值
   java -jar ultravnc-ini-modifier-1.0.jar 90699 ["C:/Users/tudan/Desktop/ultravnc.ini"]
   ```