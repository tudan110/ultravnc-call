# ultravnc-call
UltraVNC 调用程序

1. ### UltraVNCServerStarter（UltraVNCServer 启动器）

   参数讲解：

   1. winvnc.exe 路径

   2. 中继器 IP

   3. 中继器 Server 监听端口

   4. 工号

   ```
   java -jar ultravnc-server-starter-1.0.jar "C:/Program Files/uvnc bvba/UltraVNC/winvnc.exe" 10.20.16.48 5500 90699
   ```

2. ### UltraVNCServerStoper（UltraVNCServer 停止器）

   ```
   java -jar ultravnc-server-stoper-1.0.jar
   ```

3. ### UltraVNCViewerStarter（UltraVNCViewer 启动器）

   参数讲解：

   1. vncviewer.exe 路径

   2. 中继器 IP

   3. 中继器 Viewer 监听端口

   4. 工号

   ```
   java -jar ultravnc-viewer-starter-1.0.jar "C:/Program Files/uvnc bvba/UltraVNC/vncviewer.exe" 10.20.16.48 5901 90699
   ```

4. ### UltraVNCIniModifier（修改 UltraVNC 配置文件 ultravnc.ini）

   参数讲解：

   1. ultravnc.ini 配置文件路径

   2. 工号

   ```
   java -jar ultravnc-ini-modifier-1.0.jar "C:/Users/tudan/Desktop/ultravnc.ini" 90699
   ```