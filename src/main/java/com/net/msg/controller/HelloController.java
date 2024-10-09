package com.net.msg.controller;

import com.net.msg.enums.ResultCodeEnum;
import com.net.msg.udp.BootNettyUdpClient;
import com.net.msg.udp.UdpBroadcastSender;
import com.net.msg.udp.UdpProperties;
import com.net.msg.util.SpringUtils;
import com.net.msg.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/sendudp")
    public ResultVo sendudp(){
        // 根据名字 取 bean
        UdpBroadcastSender p = SpringUtils.getBean("udpBroadcastSender");
        p.sendBroadcast("hello");

        ResultVo result = new ResultVo();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg("成功");
        result.setData("hello");
        return result;

        //return "hello";
    }

    @GetMapping("/sendudp2")
    public ResultVo sendudp2(){
        // 根据名字 取 bean
        UdpBroadcastSender p = SpringUtils.getBean("udpBroadcastSender");
        p.send ("127.0.0.1","hello2");

        ResultVo result = new ResultVo();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg("成功");
        result.setData("hello2");
        return result;
        //return "hello2";
    }

    @GetMapping("/sendudp3")
    public ResultVo sendudp3(){
        // 根据名字 取 bean
        BootNettyUdpClient p = SpringUtils.getBean("bootNettyUdpClient");
        p.bind ("127.0.0.1","hello3");
        ResultVo result = new ResultVo();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg("成功");
        result.setData("hello3");
        return result;
//        return "hello3";
    }
}
