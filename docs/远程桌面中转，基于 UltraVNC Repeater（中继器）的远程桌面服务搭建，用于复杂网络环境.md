# 远程桌面中转，基于 UltraVNC Repeater（中继器）的远程桌面服务搭建，用于复杂网络环境

@[TOC](远程桌面中转，基于 UltraVNC Repeater（中继器）的远程桌面服务搭建)

# 概述

&ensp;&ensp;&ensp;&ensp; 常见的远程桌面服务，大多数是点对点的，需要控制端和被控制端双方互相能够连通，若不在同一个局域网内，仅仅通过内网 ip 地址和端口，是不能直接远程桌面的，所以**需要一个服务器来中转双方的数据**，这样只要大家能够连通服务器，那么任意两台 PC 都可以互相远程桌面。市面上出现的 TeamViewer 和向日葵，原理应该也是这样的。本文讲解如何使用 UltraVNC 搭建远程桌面服务器，并介绍如何用 Java 代码调用。

# 搭建 UltraVNC Repeater 中继器服务器

&ensp;&ensp;&ensp;&ensp; 可以根据服务器环境选择在 windows 上或者 linux 上面搭建，UltraVNC 官网只有 windows 端的源代码和可执行程序，本文提供 centos 版本的源代码，下面介绍。

## windows 端搭建

  1. 下载可执行文件 [ultravncrepeater1400](https://www.uvnc.com/component/jdownloads/summary/355-ultravncrepeater1400.html)
  2. 直接运行即可
     中继器服务器 ip：运行 repeater 的服务器地址
     中继器 UltraVNC Viewer 监听端口:

| 名称                            | 描述                                        |
| ------------------------------- | ------------------------------------------- |
| 中继器服务器 ip                 | 运行 repeater 的服务器地址                  |
| 中继器 UltraVNC Server 监听端口 | 5500                                        |
| 中继器 UltraVNC Viewer 监听端口 | 5901                                        |
| WEB 端管理页面                  | 浏览器中直接访问中继器 ip 地址，端口默认 80 |

## centos 端搭建

&ensp;&ensp;&ensp;&ensp;csdn 上传的资源默认 5 个积分，后来会自动增长，我修改后发现没有用，您可以下载百度云的资源

  1. 下载 uvncrepeater 源代码
     csdn: [uvncrep017-ws.tar.gz](https://download.csdn.net/download/wtudan/11649459)
     百度云：[https://pan.baidu.com/s/13OcU1QAL6eqKFnFWG8mPrw](https://pan.baidu.com/s/13OcU1QAL6eqKFnFWG8mPrw)
     提取码 1234
  2. 解压源代码文件

```
tar -xvf uvncrep017-ws.tar
```

  3. 编译并安装

```
cd uvncrep017-ws

make & make install
```

  4. 为中继器服务添加一个用户

 ```
useradd uvncrep
 ```

  5. 根据需要编辑配置文件 /etc/uvnc/uvncrepeater.ini ，默认如下

 ```
viewerport = 5901
maxsessions = 10
runasuser = uvncrep
logginglevel = 2
srvListAllow1 = 192.168.0.0 ;Allow network 192.168.x.x
srvListDeny0 = 127.0.0.1 ;Deny loopback
requirelistedserver=1
 ```

  6. 启动服务

 ```
/etc/init.d/uvncrepeater start
 ```

# 客户端安装 UltraVNC Server 和 UltraVNC Viewer

&ensp;&ensp;&ensp;&ensp; 建议安装绿色版，[下载地址](https://www.uvnc.com/component/jdownloads/summary/381-ultravnc-1224-bin-zip-all.html)，若地址失效，可去官网下载

| 名称          | 描述                 |
| ------------- | -------------------- |
| winvnc.exe    | UltraVNC Server 程序 |
| vncviewer.exe | UltraVNC Viewer 程序 |

# 启动 UltraVNC Server

&ensp;&ensp;&ensp;&ensp; 打开 cmd 命令行，切换到软件根目录，执行

```powershell
// 命令行参数如下
// id number: 被控制端编号（只能是数字，唯一，且至少三位，即最小是 100，最大是 2147483647，源码中此为 int 类型）
// ip: repeater 中继器服务器 IP
// port: 中继器 UltraVNC Server 监听端口
winvnc.exe -autoreconnect ID:[id number]-connect [repeater ip]:[server listen port] -run

// 示例如下
winvnc.exe -autoreconnect ID:10001-connect 192.168.1.2:5500 -run
```

# 启动 UltraVNC Viewer

&ensp;&ensp;&ensp;&ensp; 打开 cmd 命令行，切换到软件根目录，执行

```powershell
// 命令行参数如下
// id number: 被控制端编号（只能是数字，唯一，且至少三位，即最小是 100，最大是 2147483647，源码中此为 int 类型）
// ip: repeater 中继器服务器 IP
// port: 中继器 UltraVNC Viewer 监听端口
vncviewer.exe -proxy [repeater ip]:[viewer listen port] ID:[id number]

// 示例如下
vncviewer.exe -proxy 192.168.1.2:5901 ID:10001
```

# 用 Java 代码调用 Ultra VNC

&ensp;&ensp;&ensp;&ensp; 在 Java 程序里执行 cmd 命令，达到调用的效果，源码请参考 github: ultravnc-call
[https://github.com/tudan110/ultravnc-call](https://github.com/tudan110/ultravnc-call)

```java
Runtime.getRuntime ().exec (command);
```

# 有兴趣可以搭建 Apache Guacamole 服务器，可以在 WEB 端访问远程桌面，支持 ssh、vnc、rdp、telnet