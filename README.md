# ultravnc-call

&ensp;&ensp;&ensp;&ensp;UltraVNC calls programs, only for windows (because UltraVNC only has windows version). Build a relay server based on UltraVNC Repeater, and use this program to assist in calling UltraVNC Server and Viewer to realize the remote desktop function in a complex network environment.

&ensp;&ensp;&ensp;&ensp;2.0 version modified the command line parameters, you can use -h or -help to view the help.

## 1. How to build a relay server

&ensp;&ensp;&ensp;&ensp;Please refer to my blog post:
https://tudan.blog.csdn.net/article/details/100214799

![项目架构说明](README.assets/项目架构说明.png)

<center>Project structure description</center>

## 2. Service Description

### 2.1 ultravnc-call-core core tools

1. Pay attention to the configuration file config.properties

```properties
# Project development mode (dev: development and testing; others are release versions, if released, you can use release, there is no mandatory requirement, you can understand by reading the code)
system.develop.mode=dev
```

### 2.2 Configuration file ultravnc-call.setting

Please put it under the same level directory as the jar package, because the configuration file obtained from the "path where the current program is located"

```java
String settingPath = ClassUtils.getCurrentProgramPath() + "/ultravnc-call.setting";
```

### 2.3 ultravnc-server-starter (UltraVNCServer starter)

Explanation of parameters:

1. The number, unique, must be a number, and at least three digits (that is, the minimum is 100, the maximum is 2147483647)

2. Winvnc.exe path (optional parameter)

3. Repeater server IP (optional parameter)

4. Repeater UltraVNC Server listening port (optional parameter)

```sh
# Brackets [] represent optional parameters, if not filled, the corresponding default value in the configuration file will be used
java -jar ultravnc-server-starter-1.0.jar 90699 ["C:/Program Files/uvnc bvba/UltraVNC/winvnc.exe"] [10.20.16.48] [5500]
```

Version 2.0 modified the command line parameters

```shell
# Brackets [] represent optional parameters, if not filled, the corresponding default value in the configuration file will be used
java -jar ultravnc-server-starter-1.0.jar -id 90699 [-dir "C:/Program Files/uvnc bvba/UltraVNC/winvnc.exe"] [-ip 10.20.16.48] [-port 5500]
```

### 2.4 ultravnc-server-stoper (UltraVNCServer stopper)

Explanation of parameters:

1. Process name (optional parameter)

```shell
# Brackets [] represent optional parameters, if not filled, the corresponding default value in the configuration file will be used
java -jar ultravnc-server-stoper-1.0.jar [winvnc.exe]
```

Version 2.0 modified the command line parameters

```shell
# Brackets [] represent optional parameters, if not filled, the corresponding default value in the configuration file will be used
java -jar ultravnc-server-stoper-1.0.jar [-i winvnc.exe]
```

### 2.5 ultravnc-viewer-starter (UltraVNCViewer starter)

Explanation of parameters:

1. The number, unique, must be a number, and at least three digits (that is, the minimum is 100, the maximum is 2147483647)

2. vncviewer.exe path (optional parameter)

3. Repeater server IP (optional parameter)

4. Repeater UltraVNC Viewer monitoring port (optional parameter)

```shell
# Brackets [] represent optional parameters, if not filled, the corresponding default value in the configuration file will be used
java -jar ultravnc-viewer-starter-1.0.jar 90699 ["C:/Program Files/uvnc bvba/UltraVNC/vncviewer.exe"] [10.20.16.48] [5901]
```

Version 2.0 modified the command line parameters

```shell
# Brackets [] represent optional parameters, if not filled, the corresponding default value in the configuration file will be used
java -jar ultravnc-viewer-starter-1.0.jar -id 90699 [-dir "C:/Program Files/uvnc bvba/UltraVNC/vncviewer.exe"] [-ip 10.20.16.48] [-port 5901]
```

### 2.6 ultravnc-ini-modifier (modify UltraVNC configuration file ultravnc.ini)

&ensp;&ensp;&ensp;&ensp;For ultravnc.ini to take effect, system services must be installed. This is not recommended. You can use the first three interfaces.

Explanation of parameters:

1. The number, unique, must be a number, and at least three digits (that is, the minimum is 100, the maximum is 2147483647)

2. Ultravnc.ini configuration file path (optional parameter)

```shell
# Brackets [] represent optional parameters, if not filled, the corresponding default value in the configuration file will be used
java -jar ultravnc-ini-modifier-1.0.jar 90699 ["C:/Users/tudan/Desktop/ultravnc.ini"]
```

### 2.7 ultravnc-session-manager (session management program)

1. Get the session id

   http(restful) interface, get method

   ```http
   http://localhost:9081/usm/api/id
   ```

2. Release the session id

   http(restful) interface, delete method

   ```http
   http://localhost:9081/usm/api/id?sessionId=1536406588
   ```