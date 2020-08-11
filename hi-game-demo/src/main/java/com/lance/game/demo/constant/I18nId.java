package com.lance.game.demo.constant;

/**
 * 国际化id，这里不采用user.button.info这种格式，因为策划配置很麻烦
 *
 * @author Lance
 */
public interface I18nId {

    int ERROR = 10000; // 发生未知错误

    /************************************* 道具 *************************************/
    int ITEM_NOT_EXIST   = 20000; // 道具不存在
    int ITEM_CAN_NOT_USE = 20001; // 道具无法使用

    /************************************* 地图 *************************************/
    int WORLD_NOT_EXIST  = 30000; // 地图不存在
    int WORLD_FULL_SCENE = 30000; // 场景已满员
}
