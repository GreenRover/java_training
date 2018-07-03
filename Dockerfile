FROM rappdw/docker-java-python

ADD target ./target
ADD extras ./extras
ADD log4j.properties ./

CMD [ "python", "./extras/http_server.py", "9080", "java -Dlog4j.configuration=file:./log4j.properties -jar ./target/dateutils.jar" ]

EXPOSE 9080/tcp
