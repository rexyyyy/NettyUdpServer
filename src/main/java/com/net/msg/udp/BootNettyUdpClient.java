package com.net.msg.udp;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/*
一个 udp客户端类
 */
@Component(value = "bootNettyUdpClient")
public class BootNettyUdpClient {

    @Resource
    UdpProperties udpProperties;
    /*
    客户端发送消息
    由 定义好的 BootNettyUdpClientSimpleChannelInboundHandler 处理类 处理返回消息
     */
    public void bind(String address, String data) {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap clientBootstrap = new Bootstrap();
            clientBootstrap = clientBootstrap.group(eventLoopGroup);
            clientBootstrap = clientBootstrap.channel(NioDatagramChannel.class);
            clientBootstrap = clientBootstrap.option(ChannelOption.SO_BROADCAST, true);
            // 定义好的 BootNettyUdpClientSimpleChannelInboundHandler 处理类 处理返回消息
            clientBootstrap = clientBootstrap.handler(new BootNettyUdpClientSimpleChannelInboundHandler());

            Channel channel = clientBootstrap.bind(0).sync().channel();
            channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(data, CharsetUtil.UTF_8), new InetSocketAddress(address,udpProperties.getBport()))).sync();

            //  与BootNettyUdpClientSimpleChannelInboundHandler中的ctx.channel().id().toString()是一样的值
            //System.out.println("channel_id = "+channel.id().toString());

            //  方式一：查询等待超时 单位s  等待服务端原路返回的消息，
            //  在channelRead0(ChannelHandlerContext ctx, DatagramPacket msg)方法中收到消息后可主动关闭channel，此处等待自然释放
            channel.closeFuture().await(8000);

            //	方式二：直接等待服务端原路返回后在channelRead0(ChannelHandlerContext ctx, DatagramPacket msg)方法中收到消息后可主动关闭channe
            //  若服务端没有原路返回消息或者消息未收到将会一直等待，浪费资源
            //channel.closeFuture().sync();

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println("netty client udp close!");
            eventLoopGroup.shutdownGracefully();
        }
    }

}
