package com.lance.game.demo.module.item.model;

import com.lance.game.demo.core.exception.GameException;
import com.lance.game.demo.module.player.model.Player;

/**
 * 药水
 *
 * @author Lance
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
