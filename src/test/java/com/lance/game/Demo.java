package com.lance.game;

import com.lance.common.tool.util.TimeUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Lance
 * @since 2019/2/26 15:22
 */
public class Demo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello scheduler");
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }
}