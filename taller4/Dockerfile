FROM ubuntu:20.04

LABEL maintainer='didierLLanten'
LABEL version="0.1"
LABEL description="This is custom Docker Image for the Apache Services."

ENV CONTAINER_TIMEZONE="America/Bogota"
ENV APACHE_RUN_USER www-data
ENV APACHE_RUN_GROUP www-data
ENV APACHE_LOG_DIR /var/log/apache2
ENV APACHE_RUN_DIR /var/www/html

RUN ln -snf /usr/share/zoneinfo/$CONTAINER_TIMEZONE /etc/localtime && echo $CONTAINER_TIMEZONE > /etc/timezone

RUN apt update -y

RUN apt install -y apache2

COPY index.html /var/www/html/

CMD ["/usr/sbin/apache2", "-D", "FOREGROUND"]

EXPOSE 80