FROM ubuntu:20.04

# 安装 OpenJDK 17
RUN apt-get update && apt-get install -y openjdk-17-jdk

# 设置 JAVA_HOME 环境变量
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# 设置工作目录
WORKDIR /app

# 将构建的 JAR 文件复制到容器内
COPY target/Community-0.0.1-SNAPSHOT.jar app.jar

# 启动 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "app.jar"]

# 暴露 8080 端口
EXPOSE 8080
