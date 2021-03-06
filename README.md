<p align="center">
<img alt="14032" src="./assets/cloud.jpg" />
<br>
<b>Spring Cloud & Kubernetes & Istio</b>
<br><br>
<a title="Build Status" target="_blank" href="https://travis-ci.com/14032/cloud"><img src="https://img.shields.io/travis/14032/cloud.svg?style=flat-square"></a>
<a title="Docker Image CI" target="_blank" href="https://github.com/14032/cloud/actions"><img src="https://img.shields.io/github/workflow/status/14032/cloud/Docker%20Image%20CI?label=Actions&logo=github&style=flat-square"></a>
<a title="MIT" target="_blank" href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/license-MIT-orange.svg?style=flat-square"></a>
<br>
<a title="Kubernetes" target="_blank" href="https://kubernetes.io/docs/home/"><img src="https://img.shields.io/badge/kubernetes-1.19.0-blue?style=flat-square"></a>
<a title="Istio" target="_blank" href="https://istio.io/"><img src="https://img.shields.io/badge/istio-1.9-yellow?style=flat-square"></a>
<a title="Spring Cloud" target="_blank" href="https://cloud.spring.io/spring-cloud-gateway/reference/html/"><img src="https://img.shields.io/badge/springcloud-Greenwich-brightgreen?style=flat-square"></a>
<a title="Last Commit" target="_blank" href="https://github.com/14032/cloud/commits/master"><img src="https://img.shields.io/github/last-commit/14032/cloud?style=flat-square"></a>
<br><br>
<a title="GitHub Watchers" target="_blank" href="https://github.com/14032/cloud/watchers"><img src="https://img.shields.io/github/watchers/14032/cloud.svg?label=Watchers&style=social"></a>  
<a title="GitHub Stars" target="_blank" href="https://github.com/14032/cloud/stargazers"><img src="https://img.shields.io/github/stars/14032/cloud.svg?label=Stars&style=social"></a>  
<a title="GitHub Forks" target="_blank" href="https://github.com/14032/cloud/network/members"><img src="https://img.shields.io/github/forks/14032/cloud.svg?label=Forks&style=social"></a>  
</p>

## :bulb: 简介 

容器技术已成为互联网公司应用部署、运维的标配，作为云服务时代的研发，不可避免的要去了解 Docker、Kubernetes、Istio 这些技术，如果在你实际的工作中没有涉及到，仅仅靠阅读理论概念，熟悉起来可能比较困难一点，本示例会通过一个微服务示例，来让你在实际操作中去了解它。

在微服务示例中会提供单机版容器编排文件 docker-compose.yml，可以在单机环境下一键将微服务启动测试。并且在每个微服务中都会提供标准的 Kubernetes 资源描述文件（Service、Deployment、ReplicaSet、Pod），包括 Ingress、Istio 的部署文件，这些服务配置都已经在本地经过多轮部署测试，让你可以拿来即用，方便在自己搭建的 Kubernetes 平台中测试。

## :book: API Gateway

![image](assets/swagger.jpg) 

## :clapper: 图片展示

![image](assets/dashboard.png) 
 
![image](assets/kiali.png) 

![image](assets/devops.png) 