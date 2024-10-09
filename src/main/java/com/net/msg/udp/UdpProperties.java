package com.net.msg.udp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component(value = "udpProperties")
@ConfigurationProperties(prefix = "udp")
public class UdpProperties {
    private Integer bport;
}
