package com.lance.game.module.item.service;

import com.lance.game.module.common.constant.I18nId;
import com.lance.game.module.common.util.Assert;
import com.lance.game.module.item.model.AbstractItem;
import com.lance.game.module.item.model.AbstractUsableItem;
import com.lance.game.module.player.model.Player;
import com.lance.game.module.storage.service.IStorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2019/8/30 16:07
 */
@Service
public class ItemService implements IItemService {

    @Resource
    private IStorageService storageService;

    @Override
    public void useItem(Player player, long id) {
        AbstractItem item = storageService.getItemById(player, id);
        Assert.assertNotNull(item, I18nId.ITEM_NOT_EXIST);
        Assert.assertTrue(item.isUsable(), I18nId.ITEM_CAN_NOT_USE);

        // 使用道具
        AbstractUsableItem usableItem = (AbstractUsableItem) item;
        usableItem.verifyAndUse(player);

        // 移除道具
        storageService.removeItemById(player, id);
    }

}
