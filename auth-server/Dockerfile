FROM frolvlad/alpine-java:jdk8-slim

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