package com.lance.game.demo.module.item.manager;

import com.lance.game.demo.module.item.config.ItemConfig;
import com.lance.game.demo.module.item.dao.IItemConfigDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@Repository
public class ItemManager implements IItemManager {

    @Resource
    private IItemConfigDao itemConfigDao;

    @Override
    public ItemConfig getItemConfig(int id) {
        return itemConfigDao.getItemConfig("{id:" + id + "}");
    }
}
