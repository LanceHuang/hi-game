package com.lance.game.lab.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Lance
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EventPublisherTest.class)
public class EventPublisherTest {

    @Resource
    public EventPublisher eventPublisher;

    @Bean
    public EventPublisher eventPublisher() {
        return new SpringEventPublisher();
    }

    @EventListener
    public void doTestEvent(TestEvent event) {
        System.out.println("Receive TestEvent");
    }

    @Test
    public void testPublishEvent() {
        System.out.println("Hello world");
        eventPublisher.publishEvent(new TestEvent());
    }
}