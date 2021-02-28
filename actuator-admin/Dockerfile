FROM frolvlad/alpine-java:jdk8-slim

RUN set -eux && mkdir -p /home/
RUN set -eux && mkdir -p /home/actuator-admin
RUN set -eux && mkdir -p /opt/logs/actuator-admin
RUN set -eux && touch /opt/logs/actuator-admin/actuator-admin.log
ADD actuator-admin.jar /home/actuator-admin/actuator-admin.jar

ENV TZ=Asia/Shanghai
ENV JAVA_ENV="-Denv=docker"
ENV JAVA_OPTS="-server -Xmx256m -Xms256m -XX:+UseG1GC"
ENTRYPOINT [ "sh", "-c", "java $JAVA_ENV $JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -jar /home/actuator-admin/actuator-admin.jar" ]