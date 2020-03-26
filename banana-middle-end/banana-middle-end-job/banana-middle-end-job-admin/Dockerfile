FROM java:8
VOLUME /tmptest
ADD target/*.jar /jar/app.jar
EXPOSE 9503
EXPOSE 19503
EXPOSE 29503
EXPOSE 9998
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
ENV PARAMS=""
ENV PACKAGE=""
#ENTRYPOINT [ "java $PARAMS", "-jar", "/jar/app.jar" ]
ENTRYPOINT ["sh","-c","java $PARAMS $PACKAGE -jar /jar/app.jar"]