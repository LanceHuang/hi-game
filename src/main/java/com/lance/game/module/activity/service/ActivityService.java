package com.lance.game.module.activity.service;

import com.lance.game.module.activity.config.ActivityConfig;
import com.lance.game.module.activity.manager.ActivityManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2019/9/5 22:33
 */
@Service
public class ActivityService implements IActivityService {

    @Resource
    private ActivityManager activityManager;

    @Override
    public void init() {
        // todo 以配置为主，配置了则启动
        activityManager.init();
    }

}
