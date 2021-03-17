FROM openjdk:8-jdk-alpine
MAINTAINER mpluasgu@hotmail.com
RUN mkdir -p /var/log/msa
ARG artifactId
ARG version
ADD target/${artifactId}-${version}.jar app.jar
ENV TIMEZONE="America/Guayaquil"
RUN apk update && apk add tzdata
RUN rm -rf /etc/localtime \
    && ln -s /usr/share/zoneinfo/${TIMEZONE} /etc/localtime \
    && echo "${TIMEZONE}" > /etc/timezone
ENV JAVA_OPTS="-Xms256m -Xmx1028m -Xss256k"
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8 -jar /app.jar --spring.config
#.location=file:/application.properties" ]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8 -jar /app.jar" ]