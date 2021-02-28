FROM frolvlad/alpine-java:jdk8-slim

RUN set -eux && mkdir -p /home/
RUN set -eux && mkdir -p /home/user-server
RUN set -eux && mkdir -p /opt/logs/user-server
RUN set -eux && touch /opt/logs/user-server/user-server.log
ADD user-server.jar /home/user-server/user-server.jar

ENV TZ=Asia/Shanghai
ENV JAVA_ENV="-Denv=docker"
ENV JAVA_OPTS="-server -Xmx256m -Xms256m -XX:+UseG1GC"
ENTRYPOINT [ "sh", "-c", "java $JAVA_ENV $JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -jar /home/user-server/user-server.jar" ]