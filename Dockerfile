FROM java:8
ADD web-0.0.1-SNAPSHOT.jar /opt
RUN chmod +x /opt/web-0.0.1-SNAPSHOT.jar
CMD java -jar /opt/web-0.0.1-SNAPSHOT.jar