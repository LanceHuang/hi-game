package com.lance.game.resource;

import com.lance.game.resource.config.ResourceAutoConfiguration;
import com.lance.game.resource.manager.ItemManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Lance
 * @since 2020/12/2
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ResourceTest.class)
@ComponentScan("com.lance.game.resource")
@Import(ResourceAutoConfiguration.class)
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
