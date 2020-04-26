package com.lance.game.demo;

import com.lance.game.demo.log.LoggerUtil;
import org.springframework.stereotype.Service;

/**
 * @author Lance
 */
@Service
public class GameService implements IGameService {

    @Override
    public void start() {
        LoggerUtil.info("初始化游戏");

        LoggerUtil.info("初始化活动");
//        GameContext.getBean(IActivityService.class).init();

        LoggerUtil.info("初始化游戏完成");
    }

    @Override
    public void stop() {
        LoggerUtil.info("关闭游戏");
        LoggerUtil.info("关闭游戏完成");
    }
}
