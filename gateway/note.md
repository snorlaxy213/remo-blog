## 默认的filter功能和执行顺序
全局过滤器与默认过滤器以及自定义过滤器相比，永远是最后执行的；它的优先级只对其他全局过滤器起作用

当默认过滤器与自定义过滤器的优先级(order)一样时，优先出发默认过滤器，然后才是自定义过滤器；同类型的过滤器，出发顺序与他们在配置文件中声明的顺序一致

默认过滤器与自定义过滤器使用同样的order顺序空间，即他们会按照各自的顺序来进行排序

## 默认的filter功能和执行顺序
filters（按执行顺序）
1. AdaptCachedBodyGlobalFilter: 提供替换request 的 body的能力
2. NettyWriteResponseFilter: 具体的将被代理的服务的内容返回的类
3. ForwardPathFilter: forward协议的url替换类
4. RouteToRequestUrlFilter: 路由功能的具体执行类
5. LoadBalancerClientFilter（如果启用了eureka): lb协议的路由功能
6. WebsocketRoutingFilter:WebSocket的代理功能
7. NettyRoutingFilter:http协议的代理功能
8. ForwardRoutingFilter:将未处理的forward协议的请求交由spring来处理