package com.lance.game;

import com.lance.game.demo.GameContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.util.StopWatch;

/**
 * 启动类
 *
 * @author Lance
 * @since 2021/8/30
 */
@SpringBootApplication
@Slf4j
public class Start {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            // todo shutdownHook

            log.info("开始启动服务器...");
            new SpringApplicationBuilder(Start.class).run();
            log.info("服务器启动完成");

            log.info("开始初始化业务模块...");
            GameContext.getGameService().start();
            log.info("业务模块初始化完成");

            stopWatch.stop();
            log.info("服务器启动成功，耗时：{}ms", stopWatch.getLastTaskTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("服务器启动失败");
        }
    }
}
