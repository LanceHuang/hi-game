package com.lance.game.module.item.model;

import com.lance.game.exception.GameException;
import com.lance.game.module.player.model.Player;

/**
 * 可使用的道具
 *
 * @author Lance
 * @since 2019/8/30 16:08
 */
public abstract class AbstractUsableItem extends AbstractItem {

    @Override
    public boolean isUsable() {
        return true;
    }

    /**
     * 校验道具是否满足使用条件
     *
     * @throws GameException 不满足使用条件
     */
    abstract void verify(Player player) throws GameException;

    // todo 后面要不要改成使用多个？

    /**
     * 使用道具
     */
    abstract void use(Player player);

    /**
     * 校验并使用道具
     */
    public void verifyAndUse(Player player) throws GameException {
        verify(player);
        use(player);
    }
}
