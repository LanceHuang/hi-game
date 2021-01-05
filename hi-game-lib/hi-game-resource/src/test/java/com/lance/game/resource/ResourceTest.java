package com.lance.game.resource;

import com.lance.game.resource.annotation.EnableResource;
import com.lance.game.resource.manager.ItemManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2020/12/2
 */
@SpringBootTest(classes = ResourceTest.class)
@EnableResource
public class ResourceTest {

    @Resource
    private ItemManager itemManager;

    @Test
    public void testGetItemResource() {
        System.out.println(itemManager.getItemResource(1));
    }

    @Test
    public void testGetAllItemResource() {
        itemManager.getAllItemResource().forEach(System.out::println);
    }
}
