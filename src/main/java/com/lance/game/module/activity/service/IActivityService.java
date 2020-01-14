package com.lance.game.module.activity.service;

import com.lance.game.module.activity.model.ActivityInfo;

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
     * 初始化活动
     *
     * @param activityInfo 活动信息
     */
    void initActivity(ActivityInfo activityInfo);

//    /**
//     * 判断活动是否可以开启
//     */
//    boolean initActivity(ActivityInfo activityInfo);


    // todo 创建定时器


    // todo 推送活动信息

}
