package com.net.msg.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/*
NettyUdpServer 收到客户端信息之后的处理
目前简单返回一个信息
可以根据具体需求  根据不同客户端消息  做不同的处理和回复
 */
@Slf4j
public class NettyUdpHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        try {
            ByteBuf byteBuf = packet.content();
            String str = byteBuf.toString(CharsetUtil.UTF_8);
            log.info("receive str: " + str);


            // res
            String resStr = "i'm ok";
            byte[] resBytes = resStr.getBytes("UTF-8");
            DatagramPacket resData = new DatagramPacket(Unpooled.copiedBuffer(resBytes), packet.sender());
            ctx.writeAndFlush(resData);
        } catch (Exception e) {
            log.info("netty udp "+e.toString());
        }
    }
}
