package com.lance.game.skill;

import com.lance.game.skill.model.Player;
import org.junit.Test;

/**
 * 模拟 暴雪-风暴英雄 天赋系统的实现
 * @author Lance
 * @since 2019/2/26 16:13
 */
public class SkillTest {

    @Test
    public void test() {
        System.out.println("Hello world");

        Player player = new Player();
        player.setLevel(20);

        player.select(1, 2);
        player.select(4, 7);
        player.select(7, 10);
        player.select(10, 12);
        player.select(13, 15);
        player.select(16, 17);
        player.showSkills();

        player.showCandidateSkills();
    }
}
