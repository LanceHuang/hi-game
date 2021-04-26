package com.lance.game.net.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Lance
 * @since 2021/4/26
 */
@Getter
@Setter
@ConfigurationProperties("game.message")
public class MessageProperties {

    /** 监听端口 */
    private int port;
}
