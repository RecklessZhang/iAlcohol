FROM anolis-registry.cn-zhangjiakou.cr.aliyuncs.com/openanolis/openjdk:8-8.6
COPY /target/iAlcohol-0.0.1-SNAPSHOT.jar iAlcohol.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "iAlcohol.jar"]
