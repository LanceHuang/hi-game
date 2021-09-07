package com.lance.game.lab.mud.identify;

/**
 * id生成
 *
 * @author Lance
 * @since 2021/9/7
 */
public interface IIdentifyService {

    /**
     * 生成下一个id
     *
     * @return id
     */
    long nextId();
}
