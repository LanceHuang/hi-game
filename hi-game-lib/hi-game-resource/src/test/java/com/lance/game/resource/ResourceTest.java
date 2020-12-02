package com.lance.game.resource;

import com.lance.game.resource.manager.ItemManager;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2020/12/2
 */
public class ResourceTest {

    @Resource
    private ItemManager itemManager;

    @Test
    public void test() {
        System.out.println(itemManager.getItemResource(1));
    }

    @Test
    public void testGetAll() {
        itemManager.getAllItemResource().forEach(System.out::println);
    }
}
