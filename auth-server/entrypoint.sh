#!/bin/sh
ENV="-Denv=docker"

export JAVA_OPTS="-server -Xmx256m -Xms256m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/opt/logs/gc.log"
export JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom"

exec java ${ENV} $JAVA_OPTS -jar /home/auth-server/auth-server.jar

echo "java ${ENV} $JAVA_OPTS -jar /home/auth-server/auth-server.jar"
echo "start success"
