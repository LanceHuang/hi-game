package com.lance.game.lab.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EventPublisherTest.class)
@ComponentScan("com.lance.game.lab.event")
@Import(SimpleEventConfiguration.class)
public class EventPublisherTest {

    @Resource
    private IEventBusService eventBusService;

    @Test
    public void testPublishEvent() {
        System.out.println("Hello world");
        System.out.println(Thread.currentThread());
        eventBusService.publishEvent(new TestEvent());
    }
}