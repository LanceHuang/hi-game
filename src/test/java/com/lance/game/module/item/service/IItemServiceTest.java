package com.lance.game.module.item.service;

import com.lance.game.module.item.model.AbstractItem;
import com.lance.game.module.item.model.ItemType;
import com.lance.game.module.item.model.Medicine;
import com.lance.game.module.player.model.Player;
import com.lance.game.module.player.service.IPlayerService;
import com.lance.game.module.storage.model.ItemStorage;
import com.lance.game.module.storage.service.IStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring-service.xml")
public class IItemServiceTest {

    @Resource
    private IPlayerService  playerService;
    @Resource
    private IStorageService storageService;
    @Resource
    private IItemService    itemService;

    @Test
    public void useItem() {
        long playerId = System.currentTimeMillis();
        Player player = playerService.getPlayer(playerId);

        int medicineId = 1990;
        AbstractItem medicine = storageService.createItem(medicineId);
        storageService.addItem(player, medicine);

        List<AbstractItem> items = storageService.getItemsByType(player, medicine.getType());
        for (AbstractItem item : items) {
            itemService.useItem(player, item.getId());
        }
    }
}