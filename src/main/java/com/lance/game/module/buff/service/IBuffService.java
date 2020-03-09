package com.lance.game.module.buff.service;

import com.lance.game.module.buff.model.AbstractBuff;

/**
 * @author Lance
 */
public interface IBuffService {

    /**
     * 创建buff
     *
     * @param id buffId
     */
    AbstractBuff createBuff(int id);
}
