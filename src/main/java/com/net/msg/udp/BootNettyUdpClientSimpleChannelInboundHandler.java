package com.net.msg.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class BootNettyUdpClientSimpleChannelInboundHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        try {
            String strData = msg.content().toString(CharsetUtil.UTF_8);
            //打印收到的消息
            System.out.println("back msg: "+strData);
            // 与BootNettyUdpClient中的channel.id().toString()是一样的值
            //System.out.println(ctx.channel().id().toString());
            //	收到服务端原路返回的消息后，不需要再次向服务端发送消息, 可以在这里暴力关闭，也可以在 channelReadComplete(ChannelHandlerContext ctx)内
            //  ctx.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * 	重写方法
     * 	结构：
     * 	1.public class BootNettyUdpClientSimpleChannelInboundHandler extends SimpleChannelInboundHandler<DatagramPacket>
     *
     * 	2.public abstract class SimpleChannelInboundHandler<I> extends ChannelInboundHandlerAdapter
     *
     * 	3.public class ChannelInboundHandlerAdapter extends ChannelHandlerAdapter implements ChannelInboundHandler
     *
     * 	ChannelInboundHandlerAdapter类有诸多方法可以重写，可以根据具体需求来写
     *
     */

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        //System.out.println("关闭channel");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
