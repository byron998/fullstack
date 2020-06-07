# 该镜像需要依赖的基础镜像
FROM java:8
# 将当前目录下的jar包复制到docker容器的/目录下
ADD mystock-bkg-mq-0.0.1-SNAPSHOT.jar /opt/docker-app.jar
# 运行过程中创建一个docker-app.jar文件
RUN bash -c 'touch /opt/docker-app.jar'
# 声明服务运行在8381端口
EXPOSE 8381
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/opt/docker-app.jar"]
# 指定维护者的名字
MAINTAINER byron998