package com.lance.game.mud;

import com.lance.game.mud.cmd.GameCommandType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

/**
 * @author Lance
 * @since 2021/9/7
 */
public class MudTest {

    @Autowired
    private IMudService mudService;

    @Test
    public void test() {
        /*
            Sample:
            （1）创建主城：make 1 1 5 5
            （2）创建树木：make 2 2 10 10
            （3）生产农民：execute 1 MAKE_GAME_OBJECT 3 3
            （4）农民砍树：execute 3 GATHER 2
            （5）农民回城放资源：
            （6）农民制造建筑：
         */

        // todo 玩家执行操作，修改单位的上下文；每轮tick都会检查单位的上下文，执行新的内容

        // 创建战役
        long battleId = mudService.createBattleContext();

        // 创建主城
        long mainCityId = mudService.makeGameObject(battleId, 1, 5, 5);

        // 创建树木
        long treeId = mudService.makeGameObject(battleId, 2, 10, 10);

        // 生产农民
        mudService.executeGameObject(battleId, mainCityId, GameCommandType.MAKE, Collections.emptyMap());
        mudService.executeGameObject(battleId, mainCityId, GameCommandType.MAKE, Collections.emptyMap());
    }
}
