FROM azul/zulu-openjdk-centos:8u312

VOLUME /tmp
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" >> /etc/timezone

RUN set -xe \
  && yum install -y kde-l10n-Chinese glibc-common \
  && localedef -c -f UTF-8 -i zh_CN zh_CN.UFT-8 \
  && sed -i 's/LANG="en_US.UTF-8"/LANG="zh_CN.UTF-8"/' /etc/locale.conf \
  && yum clean all

RUN set -eux && mkdir -p /home/
RUN set -eux && mkdir -p /home/auth-server
RUN set -eux && mkdir -p /opt/logs/auth-server
RUN set -eux && touch /opt/logs/auth-server/auth-server.log
ADD auth-server.jar /home/auth-server/auth-server.jar

COPY entrypoint.sh /home/auth-server/entrypoint.sh
RUN chmod +x /home/auth-server/entrypoint.sh

ENV TINI_VERSION v0.19.0
ADD https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini /tini
RUN chmod +x /tini

ENTRYPOINT ["/tini", "--", "/home/auth-server/entrypoint.sh"]