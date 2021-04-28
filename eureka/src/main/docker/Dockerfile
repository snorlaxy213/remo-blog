# From java image, version : 8
#指定仓库镜像
FROM hub.c.163.com/library/java:8-jre

# 挂载test-docker目录
VOLUME /opt/package

# COPY or ADD to image
COPY eureka.jar app.jar

RUN bash -c "touch /app.jar"
#指定项目暴露的端口号，与项目配置一样,即容器配置的暴露端口
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]