package com.lance.game.net.config;

import com.lance.game.net.server.TcpServer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author Lance
 * @since 2021/4/26
 */
@EnableConfigurationProperties(TcpMessageProperties.class)
@Import(MessageConfiguration.class)
public class TcpMessageConfiguration {

    @Bean
    public TcpServer tcpServer(TcpMessageProperties properties) {
        return new TcpServer(properties);
    }
}
