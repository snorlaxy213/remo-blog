### Eureka的自我保护模式

默认情况下，如果eureka server在一定时间内（90s）没有接收到每个微服务实例的心跳，eureka server将会注销该实例。

当网络分区故障发生时，微服务与eureka server之间无法正常通信，以上行为可能变得非常危险了，因为微服务本身其实是健康的，此时本不应该注销这个微服务。eureka通过“自我保护模式来解决这个问题”。

在自我保护模式中，eureka server会保护服务注册表中的信息，不再注销任何服务实例。当它收到的心跳数重新恢复到阀值以上时，该eureka server节点就会自动退出自我保护模式。

可以使用**eureka.serverenable-self-preservation=false**禁用自我保护模式。