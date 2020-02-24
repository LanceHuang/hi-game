package com.lance.game.module.activity.service;

import com.lance.game.module.activity.config.ActivityConfig;
import com.lance.game.module.activity.manager.ActivityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = ActivityServiceTest.class) // todo 不知道需不需要
@ComponentScan({"com.lance.game.module.activity"})
public class ActivityServiceTest {

    @Resource
    private ActivityManager activityManager;

    @Resource
    private IActivityService activityService;

    @Before
    public void before() {
        addActivityConfig(10000001, 10000, "测试活动1-第一期", "2020-01-17 18:18:00", "2020-01-17 18:18:30");
        addActivityConfig(10000002, 10000, "测试活动1-第二期", "2020-01-17 18:19:00", "2020-01-17 17:19:30"); // Error
//        addActivityConfig(10001001, 10001, "测试活动2-第一期", "2020-01-17 17:49:00", "2020-01-17 17:49:30");
//        addActivityConfig(10002001, 10002, "测试活动3-第一期", "2020-01-17 17:49:00", "2020-01-17 17:49:30");
    }

    private void addActivityConfig(int id, int type, String name, String startTime, String stopTime) {
//        ActivityConfig testActivityConfig = new ActivityConfig();
//        testActivityConfig.setId(id);
//        testActivityConfig.setType(type);
//        testActivityConfig.setName(name);
//        testActivityConfig.setStartTime(startTime);
//        testActivityConfig.setStopTime(stopTime);
//        activityManager.getActivityConfigs().put(testActivityConfig.getId(), testActivityConfig);
    }

    @Test
    public void test() throws InterruptedException {
        activityService.init();

        System.out.println("Init activities completely");
        TimeUnit.SECONDS.sleep(200L); // 主线程退出后，子线程也会退出，这里sleep用于保证活动能正常开启关闭
        System.out.println("Finished...");
    }

}