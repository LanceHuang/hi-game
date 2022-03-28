package com.lance.game.benchmark;

import com.lance.game.event.config.EventConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Lance
 * @since 2022/3/28
 */
@SpringBootApplication
@Import(EventConfiguration.class)
public class BenchMarkApp {
}
