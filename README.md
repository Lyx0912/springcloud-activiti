# springcloud-Activiti
个人练手写的Springcloud脚手架，使用Springcloud Alibaba+Activiti7+SpringSecurity Oauth2 +Vue+Redis。实现了动态权限，统一认证服务+网关统一鉴权。同时整合Activiti7工作流模块。

### 项目结构

cloud-activiti:Activiti7工作流服务，提供对模型，部署，定义和流程管理。

cloud-admin:系统服务，包括用户，角色，权限，客户端信息的管理。

cloud-attendance:考勤服务，测试工作流服务，主要部署一些请假，出差的流程进行管理。

cloud-common:公共模块，提供公用的API和类，其他模块引入，例如统一的返回对象，公共类等。

cloud-gateway:网关服务，负责拦截转发请求，进行鉴权。

前端地址：https://github.com/Lyx0912/springcloud-activiti-vue

### 运行结果

用户管理

![职工管理](D:\code\WorkSpace\lyx-cloud\springcloud-activiti\doc\职工管理.jpg)

角色管理

![角色管理](D:\code\WorkSpace\lyx-cloud\springcloud-activiti\doc\角色管理.jpg)

菜单管理

![菜单管理](D:\code\WorkSpace\lyx-cloud\springcloud-activiti\doc\菜单管理.jpg)

模型管理

![模型管理](D:\code\WorkSpace\lyx-cloud\springcloud-activiti\doc\模型管理.jpg)

部署管理

![部署管理](D:\code\WorkSpace\lyx-cloud\springcloud-activiti\doc\部署管理.jpg)

在线流程设计

![在线流程设计](D:\code\WorkSpace\lyx-cloud\springcloud-activiti\doc\在线流程设计.jpg)

我的待办

![我的待办](D:\code\WorkSpace\lyx-cloud\springcloud-activiti\doc\我的待办.jpg)

流程进度查询

![流程进度查询](D:\code\WorkSpace\lyx-cloud\springcloud-activiti\doc\流程进度查询.jpg)
