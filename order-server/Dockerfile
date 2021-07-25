FROM azul/zulu-openjdk-centos:8u312

VOLUME /tmp
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" >> /etc/timezone

RUN set -xe \
  && yum install -y kde-l10n-Chinese glibc-common \
  && localedef -c -f UTF-8 -i zh_CN zh_CN.UFT-8 \
  && sed -i 's/LANG="en_US.UTF-8"/LANG="zh_CN.UTF-8"/' /etc/locale.conf \
  && yum clean all

RUN set -eux && mkdir -p /home/order-server
RUN set -eux && mkdir -p /opt/logs/order-server
RUN set -eux && touch /opt/logs/order-server/order-server.log
ADD order-server.jar /home/order-server/order-server.jar

ENV TZ=Asia/Shanghai
ENV JAVA_ENV="-Denv=docker"
ENV JAVA_OPTS="-server -Xmx256m -Xms256m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/opt/logs/gc.log"

ENTRYPOINT ["sh", "-c", "java $JAVA_ENV $JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -jar /home/order-server/order-server.jar" ]