# 使用官方Java 8镜像作为基础镜像
FROM openjdk:8-jdk-alpine
 
# 复制本地的Java应用到容器内
COPY ./weile-seo-0.0.1-SNAPSHOT.jar app/app.jar
 
# 设置工作目录
WORKDIR /app

EXPOSE 8080
 
# 指定容器启动时运行的命令
CMD ["java", "-jar", "app.jar"]
