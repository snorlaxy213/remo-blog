# From java image, version : 8
#指定仓库镜像
FROM openjdk:latest

ARG JAR_FILE=target/*.jar

# COPY or ADD to image
COPY ${JAR_FILE} app.jar

# 激活项目配置文件
CMD ["--spring.profiles.active=server-one"]

#指定项目暴露的端口号，与项目配置一样,即容器配置的暴露端口
EXPOSE 8761

#修改文件的创建修改时间
RUN bash -c "touch /app.jar"

#运行脚本，启动springboot项目，这里我们指定加载配置文件的位置，并且通过数据卷挂载同步到容器中
ENTRYPOINT ["java", "-jar", "app.jar"]
