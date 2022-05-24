
# 开发助手-RICKAPI


稳定、快速、免费的 API 接口服务

<div align="center">
   <img width="160" src="http://q1.qlogo.cn/g?b=qq&nk=210401532&s=640" alt="logo"></br>
</div>


----

## 声明

**一切开发旨在学习**，**请勿用于非法用途** 👩‍💻

## 项目技术

1. 前台文档: **gitbook**
2. 前台api: **php**
3. 后台前端 :**vue vuex vue-router axios echart**
4. 后台后端:  **springboot redis mybatis** 

## API 介绍

研发人员A开发了软件A，研发人员B正在研发软件B。

有一天，研发人员B想要调用软件A的部分功能来用，但是他又不想从头看一遍软件A的源码和功能实现过程，怎么办呢？

研发人员A想了一个好主意：我把软件A里你需要的功能打包好，写成一个函数；你按照我说的流程，把这个函数放在软件B里，就能直接用我的功能了！

其中，API就是研发人员A说的那个函数。



## 项目功能

### 编写的api 功能如图:

- ![image-20220521234616456](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212346568.png)
- ![image-20220521234611407](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212346521.png)

### 后台管理功能如下:

- 登录 / 注销
- 权限验证
- sso单点登录
- 全局功能
  - api状态管理
  - api数据管理
  - sso单点登录
  - token续命
  - 用户管理
- 日志管理
  - 登录日志
  - 操作日志
- api管理
  - 启用
  - 停用
  - ECharts 图表统计




## 项目截图

### 前台文档介绍

![image-20220521230058137](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212301311.png)

![image-20220521230121090](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212301204.png)

![image-20220521230155120](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212301251.png)

### 前台api接口

![image-20220521230258576](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212302688.png)

![image-20220521230253720](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212302822.png)

### 后台前端

![image-20220521235128397](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212351567.png)

![image-20220521235245502](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212352629.png)

![image-20220521235236571](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212352711.png)

### 后台后端

![image-20220521230651041](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212306168.png)

![image-20220521230645763](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212306900.png)


## 项目配置介绍

### 项目环境要求

1. 前端api :  **ngnix **    **php>7.1**   推荐 使用phpstudy or直接服务器

2. 前端docs文档 : **node.js <12.2    gitbook<3.0**  

3. 后台后端: **jdk=11     redis   ** **mysql>8.0**

4. 后台前端: **vue>3.0**


### 项目环境运行

1. 前端api : 将文件直接 放入 phpstudy 即可 

   

2. 前端docs文档 : 
	- 官网下载node.js   
	
	- 安装gitbook :npm install gitbook-cli -g
	
	- 在根目录文件内安装插件:gitbook install 
	
	- 然后运行 :gitbook serve
	
	  
	
3. 后台后端:  

	- 将项目导入idea  
	- 刷新maven 导入依赖
	- 下载安装redis  记住密码
	- 将数据库文件导入数据库 库名为 springboot-vue
	- 配置application.yml  (redis 和 mysql)
	- ![image-20220521232922460](https://cdn.jsdelivr.net/gh/rickhqh/pic/img/202205212329539.png)
	- 然后运行


4. 后台前端: 
	- 安装vue3.0 : npm install -g @vue/cli 
	
	- 在根目录文件内安装依赖 :npm install
	
	- 运行:npm run serve
	







