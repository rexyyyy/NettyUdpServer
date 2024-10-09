package com.net.msg.udp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/*
Springboot 服务启动时 同时启动 udp server 监听端口application.yml 的 udp.bport
 */
@Component
public class UdpStarter implements CommandLineRunner {

    @Resource
    private UdpProperties udpProperties;

    @Override
    public void run(String... args) throws Exception {
        NettyUdpServer nettyUdpServer = new NettyUdpServer();
        // 监听端口application.yml 的 udp.bport
        nettyUdpServer.init(udpProperties.getBport());
    }
}
