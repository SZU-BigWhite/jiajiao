FROM java:8
ADD jiajiao-1.0.jar /opt
RUN chmod +x /opt/jiajiao-1.0.jar
EXPOSE 8091
ENTRYPOINT java -jar -Xms512m -Xmx512m -Xmn256m /opt/jiajiao-1.0.jar