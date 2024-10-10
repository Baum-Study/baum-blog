FROM openjdk:21-jdk
ENV TZ=Asia/Seoul
COPY build/libs/blog-0.0.1.jar blog.jar
EXPOSE 8080
CMD ["java", "-jar", "blog.jar"]
