# base images
FROM java:8
# run mysql
RUN -d --name mysql-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql
# run rabbit mq
RUN -d --name rabbitmq -p 5672:5672 -p 15672:15672 -v `pwd`/data:/var/lib/rabbitmq --hostname myRabbit -e RABBITMQ_DEFAULT_VHOST=my_vhost  -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin
# add jar
ADD mystock-bkg-mq-0.0.1-SNAPSHOT.jar /opt/docker-app.jar
# rename
RUN bash -c 'touch /opt/docker-app.jar'

# RUN for working dir
RUN mkdir -p /usr/local/project
WORKDIR /usr/local/project

# working on port
EXPOSE 8381
# ָrun java -jar
ENTRYPOINT ["java", "-jar","/opt/docker-app.jar"]
# maintainer name e-mail
MAINTAINER byron998 "byron998@gmail.com"