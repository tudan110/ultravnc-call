# ultravnc-call
UltraVNC 调用程序，仅适用于 windows（因为 UltraVNC 只有 windows 版本）

1. ### UltraVNCServerStarter（UltraVNCServer 启动器）

   参数讲解：

   1. 工号

   2. winvnc.exe 路径（可选参数）

   3. 中继器服务器 IP（可选参数）

   4. 中继器 UltraVNC Server 监听端口（可选参数）

   ```
   # 中括号[]代表可选参数，若不填，则用配置文件中对应的默认值
   java -jar ultravnc-server-starter-1.0.jar "C:/Program Files/uvnc bvba/UltraVNC/winvnc.exe" 90699 [10.20.16.48] [5500]
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

   1. 工号

   2. vncviewer.exe 路径（可选参数）

   3. 中继器服务器 IP（可选参数）

   4. 中继器 UltraVNC Viewer 监听端口（可选参数）

   ```
   # 中括号[]代表可选参数，若不填，则用配置文件中对应的默认值
   java -jar ultravnc-viewer-starter-1.0.jar "C:/Program Files/uvnc bvba/UltraVNC/vncviewer.exe" 90699 [10.20.16.48] [5901]
   ```

4. ### UltraVNCIniModifier（修改 UltraVNC 配置文件 ultravnc.ini）

   参数讲解：

   1. ultravnc.ini 配置文件路径

   2. 工号

   ```
   java -jar ultravnc-ini-modifier-1.0.jar "C:/Users/tudan/Desktop/ultravnc.ini" 90699
   ```