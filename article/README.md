### 集成Feign

Step1 ： 引入Feign依赖

````java
<!-- 开启feign-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
````

Step2 ： 创建Feign接口，并添加@FeignClient注解

````java
@FeignClient(name = "log", configuration = FeignConfig.class)
public interface LogServiceClient {

    @PostMapping(value = "/saveLog")
    void saveSysLog(@RequestBody SysLog sysLog);

}
````

由于使用了eureka，@FeignClient的name会解析成对应eureka服务注册表中的服务；

若没有使用eureka，可用service.ribbon.listOfServers属性配置服务器列表。

还可以使用属性指定的URL，例如@FeignClient(name = "log", url= "http://localhost:8080/")

tips：@FeignClient(configuration = FeignConfig.class)为自定义Feign配置

Step3 ： 使用Feign接口

````java
    @Autowired
    private LogServiceClient logServiceClient;

    method(){
        logServiceClient.saveSysLog(sysLog);
    }   
````

Step4 ： 启动类添加注解@EnableFeignClients

