#保证该脚本能被chkconfig识别
#!/bin/sh
# chkconfig: 2345 85 15
# description:auto_run

#程序名
RUN_NAME="ultravnc-session-manager-1.0.jar"

#jdk /usr/local/java/jdk1.8.0_191
#jar 位置
JAVA_OPTS=/home/monitor/zhkhd/package-for-smart-it-pro/ultravnc-session-manager-1.0.jar
LOG_OPTS=/home/monitor/zhkhd/package-for-smart-it-pro/logs/usm.log

#开始方法
start() {
        nohup java -jar $JAVA_OPTS >$LOG_OPTS 2>&1 &
        echo "$RUN_NAME started success."
}

#结束方法
stop() {
        echo "stopping $RUN_NAME ..."
        kill -9 `ps -ef|grep $JAVA_OPTS|grep -v grep|grep -v stop|awk '{print $2}'`
}

case "$1" in
        start)
            start
            ;;
        stop)
            stop
            ;;
        restart)
            stop
            start
            ;;
        *)
                echo "Userage: $0 {start|stop|restart}"
                exit 1
esac