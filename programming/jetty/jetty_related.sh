# 定义两个变量，也可以是环境变量

# jetty安装目录
JETTY_HOME=/Develop/servers/jetty

#jetty实例的配置，日志和应用
JETTY_BASE=/Develop/servers/mybase


#启动jetty
cd $JETTY_BASE
#激活了http和deploy模块
java -jar $JETTY_HOME/start.jar --and-to-startd=http,deploy
#                                           ^ 这里不是started啊

#这个时候会生成start.d目录和webapps目录

#拷贝到webapps中的war会被deploy模块热部署到jetty中
cp $JETTY_HOME/demo-base/webapps/async-rest.war webapps/ROOT.war
#启动jetty
java -jar $JETTY_HOME/start.jar

#修改启动port
java -jar $JETTY_HOME/start.jar jetty.http.port=8081

#或者修改start.d/http.ini中的jetty.http.port的值
vi $JETTY_BASE/start.d/http.ini


#当然我们也可以激活HTTPS和HTTP2模块
java -jar $JETTY_HOME/start.jar --and-to-startd=https,http2
#当然会在start.d中创建对应的ini文件

#如果需要只是用start.ini文件，而不是使用start.d文件夹，那么配置会append到start.ini中


# webapps中， 标准的war文件和jetty配置文件会被deploy模块热部署到jetty中
# 1. example/ 中如果还有WEB-INF目录，会被当做标准web应用部署，否则当做静态内容部署。
#    路径为http;//localhost:8080/example/
#    如果目录为ROOT/的话，则http://localhost:8080/
# 2. 如果目录为.d，则会被忽略。但是也可能会被当做配置文件。
# 3. example.xml会被当做配置文件部署，如果同时存在同名的xml和war，只有xml会被部署，war可能
#    会在xml中使用



# 配置方式

# 1 ini文件



# 2 mod文件



# 3 xml文件



# 配置内容

# 1 /etc/jetty.xml

# ThreadPool ...

# Connector
# port 监听那个端口
# host 监听那个network interface，或者默认0.0.0.0监听所有接口
# Idle Timeout 连接空闲的时间
#
