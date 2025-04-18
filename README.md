# KK项目（暂时先以此为代号）

KK 项目，是基于Spring Cloud Alibaba + Dubbo 的一套开源的微服务分布式架构，致力于提供微服务开发的一站式业务解决方案。此项目包含开发分布式应用微服务的必需组件，方便开发者通过 Spring Cloud Alibaba + Dubbo 快速构建SaaS系统，轻松使用这些项目来搭建及开发分布式应用服务。

依托 Spring Cloud Alibaba，您只需要添加一些注解和少量配置，就可以将 Spring Cloud 应用接入阿里微服务解决方案，通过阿里中间件来迅速搭建分布式应用系统。
通过 Dubbo 可以高效的将微服务串联到业务模块中。

本项目为分布式微服务架构，主要采用spring boot3x版本的web后端服务开发模板，整合了众多常用依赖，以及起步功能，适合中作为中大项目的工程。
## 版本介绍
- **java17**
- **maven3.6.1**
- **springboot3.2.2**
- **其他依赖均采用较新的版本目前并未发现存在依赖冲突、版本不兼容问题**

## 主要技术要点

* **服务限流降级**：默认支持 WebServlet、WebFlux、OpenFeign、RestTemplate、Spring Cloud Gateway、Dubbo 和 RocketMQ 限流降级功能的接入，可以在运行时通过控制台实时修改限流降级规则，还支持查看限流降级 Metrics 监控。
* **服务注册与发现**：适配 Spring Cloud 服务注册与发现标准，默认集成对应 Spring Cloud 版本所支持的负载均衡组件的适配。
* **分布式配置管理**：支持分布式系统中的外部化配置，配置更改时自动刷新。
* **消息驱动能力**：基于 Spring Cloud Stream 为微服务应用构建消息驱动能力。
* **分布式事务**：使用 @GlobalTransactional 注解， 高效并且对业务零侵入地解决分布式事务问题。
* **阿里云对象存储**：阿里云提供的海量、安全、低成本、高可靠的云存储服务。支持在任何应用、任何时间、任何地点存储和访问任意类型的数据。
* **分布式任务调度**：提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。同时提供分布式的任务执行模型，如网格任务。网格任务支持海量子任务均匀分配到所有 Worker（schedulerx-client）上执行。
* **阿里云短信服务**：覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。

提供支持高并发的分布式业务系统。

## 主要业务要点
功能持续开发中，集中于SaaS的分布式业务系统构建，将提供:
* **会员管理**：实现无论任何场景都能达到会员识别、获取、支付等的能力，设置会员储值规则、积分等能力。
* **营销管理**：提供一体化优惠券、会员行为营销活动，为会员提供拉新、留存、裂变等业务功能。
* **商品管理**：针对多渠道商品优化管理工具。
* **订单管理**：订单中心承接全部订单。
* **履约管理**：对接三方物流平台的开放api，提供一站式的订单配送管理能力。
* **财务管理**：支持订单在线开具电子发票等。
* **数据报表**：业务数据全部汇总，方便进行分析，比如会员消费报表、储值明细报表、会员积分报表等。
* **小微商城**：基于微信平台提供小程序购物的能力。
* **系统管理**：实现商户的账号、部门、角色、权限、门店等信息的管理。

子模块层级关系：
spring-cloud-alibaba-dependencies： 是spring-cloud-alibaba的依赖定义，是最高的，不依赖其它的项目
kk-arch-bom-dependencies：是kk-arch-bom的依赖定义，是所有其它项目的依赖版本定义，由其它项目（包括其父项目）import，松藕合
kk-arch-common-dependencies：是一些工具类的定义
kk-arch-dubbo-dependencies：是dubbo的依赖定义，提供给dubbo-service和dubbo-web子模块依赖引入
kk-arch-dubbo-service-dependencies：是service子模块的依赖定义，是所有dubbo相关的service项目的依赖定义
kk-arch-dubbo-web-dependencies：是web子模块的依赖定义，是所有dubbo相关的web项目的依赖定义
kk-arch-remote-dependencies：是remote子模块的依赖定义，是所有remote项目的依赖定义
kk-arch: 顶级项目，引入了kk-arch-bom-dependencies，以管理其他子模块的依赖的版本


营销：

dubbo.application.name=KkMarketingWeb
dubbo.protocol.port=20921
spring.application.name=kk-order-web
server.port=18021

dubbo.application.name=MarketingCouponService
dubbo.protocol.port=20920
spring.application.name=kk-marketing-coupon-service
server.port=18020

订单：

dubbo.application.name=OrderWebApplication
dubbo.protocol.port=20931
spring.application.name=kk-order-web
server.port=18031

dubbo.application.name=OrderCenterService
dubbo.protocol.port=20930
spring.application.name=kk-order-center-service
server.port=18030

