# NettyUdpServer

#### 介绍
基于 Springboot  集成 Netty， 在Springboot mvc 的基础上，再搭建一个udp server，支持发送udp 消息 udp 广播消息等。
由于使用 Netty框架，极大提高了udp开发的便捷性，有比较好的运行性能

#### 软件架构
1.Springboot  
org.springframework:spring-core:5.3.5
org.springframework.boot:spring-boot:2.4.4
2.Netty
io.netty:netty-all:4.1.60Final

#### 安装教程
package as JAR 


#### 使用例子
>向 255.255.255.255 发送广播消息
>http://127.0.0.1:8081/test/sendudp

####  例子
>向 127.0.0.1 发送消息
>http://127.0.0.1:8081/test/sendudp2

####  例子
>向 127.0.0.1 发送消息
>http://127.0.0.1:8081/test/sendudp3
>定义 BootNettyUdpClient 向 ip发送消息, BootNettyUdpClient中定义处理服务器返回消息处理器
#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request
