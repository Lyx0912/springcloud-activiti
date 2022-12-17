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

![职工管理](https://github.com/Lyx0912/springcloud-activiti/blob/main/doc/%E8%81%8C%E5%B7%A5%E7%AE%A1%E7%90%86.jpg?raw=true)

角色管理

![角色管理](https://github.com/Lyx0912/springcloud-activiti/blob/main/doc/%E8%A7%92%E8%89%B2%E7%AE%A1%E7%90%86.jpg?raw=true)

菜单管理

![菜单管理](https://github.com/Lyx0912/springcloud-activiti/blob/main/doc/%E8%8F%9C%E5%8D%95%E7%AE%A1%E7%90%86.jpg?raw=true)

模型管理

![模型管理](https://github.com/Lyx0912/springcloud-activiti/blob/main/doc/%E6%A8%A1%E5%9E%8B%E7%AE%A1%E7%90%86.jpg?raw=true)

部署管理

![部署管理](https://github.com/Lyx0912/springcloud-activiti/blob/main/doc/%E9%83%A8%E7%BD%B2%E7%AE%A1%E7%90%86.jpg?raw=true)

在线流程设计

![在线流程设计](https://github.com/Lyx0912/springcloud-activiti/blob/main/doc/%E5%9C%A8%E7%BA%BF%E6%B5%81%E7%A8%8B%E8%AE%BE%E8%AE%A1.jpg?raw=true)

我的待办

![我的待办](https://github.com/Lyx0912/springcloud-activiti/blob/main/doc/%E6%88%91%E7%9A%84%E5%BE%85%E5%8A%9E.jpg?raw=true)

流程进度查询

![流程进度查询](https://github.com/Lyx0912/springcloud-activiti/blob/main/doc/%E6%B5%81%E7%A8%8B%E8%BF%9B%E5%BA%A6%E6%9F%A5%E8%AF%A2.jpg?raw=true)
