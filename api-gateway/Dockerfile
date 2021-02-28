FROM frolvlad/alpine-java:jdk8-slim

RUN set -eux && mkdir -p /home/api-gateway
RUN set -eux && mkdir -p /opt/logs/api-gateway
RUN set -eux && touch /opt/logs/api-gateway/api-gateway.log
ADD api-gateway.jar /home/api-gateway/api-gateway.jar

ENV TZ=Asia/Shanghai
ENV JAVA_ENV="-Denv=docker"
ENV JAVA_OPTS="-server -Xmx256m -Xms256m -XX:+UseG1GC"

ENTRYPOINT ["sh", "-c", "java $JAVA_ENV $JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -jar /home/api-gateway/api-gateway.jar" ]