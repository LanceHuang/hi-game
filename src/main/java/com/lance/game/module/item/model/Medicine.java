package com.lance.game.module.item.model;

import com.lance.game.module.common.exception.GameException;
import com.lance.game.module.player.model.Player;

/**
 * 药物
 *
 * @author Lance
 * @since 2019/7/4 20:58
 */
public class Medicine extends AbstractUsableItem {

    // todo 希望是通过配置的方式
    @Override
    void verify(Player player) throws GameException {
        System.out.println("Verify medicine");
    }

    // todo 不同的药物效果，希望能直接配置
    @Override
    void use(Player player) {
        System.out.println("Use medicine");
    }
}
