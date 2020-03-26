FROM java:8
VOLUME /tmptest
ADD target/*.jar /jar/app.jar
EXPOSE 9504
EXPOSE 19504
EXPOSE 29504
EXPOSE 9514
EXPOSE 19514
EXPOSE 29514
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
ENV PARAMS=""
ENV PACKAGE=""
#ENTRYPOINT [ "java $PARAMS", "-jar", "/jar/app.jar" ]
ENTRYPOINT ["sh","-c","java $PARAMS $PACKAGE -jar /jar/app.jar"]