package com.lance.game.lab.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EventPublisherTest.class)
@EnableEvent
public class EventPublisherTest {

    @Resource
    public EventPublisher eventPublisher;

    @EventListener
    public void onTestEvent(TestEvent event) {
        System.out.println("Receive TestEvent");
        System.out.println(this);
    }

    @Test
    public void testPublishEvent() {
        System.out.println("Hello world");
        eventPublisher.publishEvent(new TestEvent());
    }
}