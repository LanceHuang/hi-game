package com.lance.game.demo;

import com.lance.game.demo.core.log.LoggerUtil;
import org.springframework.stereotype.Service;

/**
 * @author Lance
 * @since 2021/8/31
 */
@Service
public class GameServiceImpl implements GameService {

    @Override
    public void start() {
        LoggerUtil.info("初始化游戏");

        LoggerUtil.info("初始化活动");

        LoggerUtil.info("初始化游戏完成");
    }

    @Override
    public void stop() {
        LoggerUtil.info("关闭游戏");
        LoggerUtil.info("关闭游戏完成");
    }
}
