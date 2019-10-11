# 现场安装，设置 会话管理服务 为开机自启动

chmod +x /etc/init.d/usm.sh
chmod +x /etc/init.d/ultravnc-session-manager-1.0.jar

chkconfig --add usm.sh   (添加为系统服务)
chkconfig usm.sh on  （开机自启动）
service usm.sh start（启动服务）