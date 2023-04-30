FROM redis

ENV SENTINEL_QUORUM 2
ENV SENTINEL_DOWN_AFTER 1000
ENV SENTINEL_FAILOVER 1000

RUN mkdir -p /redis
RUN apt-get update && apt-get install -y nmap procps

WORKDIR /redis

COPY sentinel.conf .
COPY sentinel-entrypoint.sh /usr/local/bin/

RUN chown redis:redis /redis/* && chmod +x /usr/local/bin/sentinel-entrypoint.sh

EXPOSE 26379

ENTRYPOINT ["sentinel-entrypoint.sh"]
