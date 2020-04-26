package com.lance.game.demo.constant;

/**
 * 国际化id，这类不采用user.button.info这种格式。
 * 当然，也可以在这里手动分组，我个人不是很想再加一个内部类
 *
 * @author Lance
 * @since 2019/8/30 17:00
 */
public interface I18nId {

    int ERROR = 10000; // 发生未知错误

    int ITEM_NOT_EXIST   = 20000; // 道具不存在
    int ITEM_CAN_NOT_USE = 20001; // 道具无法使用

}
