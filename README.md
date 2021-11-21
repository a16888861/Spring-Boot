# Spring-Boot Blog
Spring-Boot 框架搭建的博客后台，目前实现用户注册和单点登录

##### 后台接口文档地址：http://localhost:7000/doc.html

##### 版本介绍
    1.JAVA版本号：1.8
    2.MAVEN版本号：3.8.1
    3.Redis版本号：6.2.5
    4.Spring Boot版本号：2.5.5
    5.文档采用Knife4j-版本号：3.0.3
    6.日志文件采用log4j2版本号：2.5.5

##### 项目部分文件夹说明
    1.公共定时任务位置：com.kali.blog.common.CommonScheduling

##### docker启动相关环境
    1.拉取镜像
    docker pull redis
    docker pull daocloud.io/library/mysql:8.0.21
    2.根据实例启动镜像
    docker run -d -p 6379:6379 --name redis-6.2.5 7faa --appendonly yes
    docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 --name mysql-8.0.21 8e85
    3.批量启动
    docker start $(docker ps -a | awk '{ print $1}' | tail -n +2)

##### Issue
    docker-compose文件夹放置了已经配置好的docker-compose.yml文件
    如需使用，请手动指定文件或删除后缀

##### Issue
    1.如使用docker命令遇到权限问题，可通过将用户添加到docker用户组可以将sudo去掉，命令如下
    groupadd docker #添加docker用户组
    gpasswd -a $USER docker #将登陆用户加入到docker用户组中
    newgrp docker #更新用户组
    2.mysql-8.0以上版本如连接时出现Public Key Retrieval is not allowed 需在连接url上加 allowPublicKeyRetrieval=true