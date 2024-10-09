package com.net.msg.udp;

import com.net.msg.util.SpringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
发送 udp信息
 */
@Component(value = "udpBroadcastSender")
public class UdpBroadcastSender {


    /*
    向局域网 默认发广播信息
    局域网所有客户端都能
     */
    public void sendBroadcast(String BROADCAST_MESSAGE) {

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
            byte[] message = BROADCAST_MESSAGE.getBytes();
            // 根据 类型 取 bean
            UdpProperties p = SpringUtils.getBean(UdpProperties.class);
            DatagramPacket packet = new DatagramPacket(
                    message, message.length, broadcastAddress, p.getBport());

            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    向指定ip 发送udp消息
    需要指定ip有启动 udp server 监听
     */
    public void send(String ip,String BROADCAST_MESSAGE) {

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress broadcastAddress = InetAddress.getByName(ip);
            byte[] message = BROADCAST_MESSAGE.getBytes();
            // 根据 类型 取 bean
            UdpProperties p = SpringUtils.getBean(UdpProperties.class);
            DatagramPacket packet = new DatagramPacket(
                    message, message.length, broadcastAddress, p.getBport());

            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
