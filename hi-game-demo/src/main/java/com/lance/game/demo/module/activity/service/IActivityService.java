package com.lance.game.demo.module.activity.service;

import com.lance.game.demo.module.activity.model.ActivityInfo;

/**
 * 活动
 *
 * @author Lance
 * @since 2019/9/5 20:42
 */
public interface IActivityService {

    /**
     * 初始化
     */
    void init();

    /**
     * 开启活动
     *
     * @param activityInfo 活动信息
     */
    void startActivity(ActivityInfo activityInfo);

    /**
     * 关闭活动
     *
     * @param activityInfo 活动信息
     */
    void stopActivity(ActivityInfo activityInfo);

    // todo 推送活动信息

}
