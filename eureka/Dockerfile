FROM frolvlad/alpine-java:jdk8-slim

RUN set -eux && mkdir -p /home/
RUN set -eux && mkdir -p /home/eureka-server
RUN set -eux && mkdir -p /opt/logs/eureka-server
RUN set -eux && touch /opt/logs/eureka-server/eureka-server.log
ADD eureka-server.jar /home/eureka-server/eureka-server.jar

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-server -Xmx256m -Xms256m -XX:+UseG1GC"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -jar /home/eureka-server/eureka-server.jar" ]