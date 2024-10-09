package com.net.msg.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import lombok.extern.slf4j.Slf4j;

/*
NettyUdpServer 监听端口application.yml 的 udp.bport
 */
@Slf4j
public class NettyUdpServer {
    /**
     * 启动服务
     */
    public void init(int port) {
        //表示服务器连接监听线程组，专门接受 accept 新的客户端client 连接
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup();

        // 连接管理线程池


        try {
            //1、创建netty bootstrap 启动类
            Bootstrap serverBootstrap = new Bootstrap();
            //2、设置boostrap 的eventLoopGroup线程组
            serverBootstrap = serverBootstrap.group(bossLoopGroup);
            //3、设置NIO UDP连接通道
            serverBootstrap = serverBootstrap.channel(NioDatagramChannel.class);
            //4、设置通道参数 SO_BROADCAST广播形式
            int BUF_ZISE = 1024 * 1024 * 30;
            serverBootstrap = serverBootstrap.option(ChannelOption.SO_RCVBUF, BUF_ZISE);
            serverBootstrap = serverBootstrap.option(ChannelOption.SO_SNDBUF, BUF_ZISE);
            serverBootstrap = serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            serverBootstrap = serverBootstrap.option(ChannelOption.SO_BROADCAST, true);
            //5、设置处理类 装配流水线
//            serverBootstrap = serverBootstrap.
            serverBootstrap = serverBootstrap.handler(new NettyUdpHandler());
            //6、绑定server，通过调用sync（）方法异步阻塞，直到绑定成功
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            log.info("started and listened on " + channelFuture.channel().localAddress());
            //7、监听通道关闭事件，应用程序会一直等待，直到channel关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
        } finally {

            log.info("netty udp close!");
            //8 关闭EventLoopGroup，
            bossLoopGroup.shutdownGracefully();
        }
    }

}
