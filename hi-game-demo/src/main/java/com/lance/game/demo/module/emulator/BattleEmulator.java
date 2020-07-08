package com.lance.game.demo.module.emulator;

/**
 * 战斗模拟器：
 * <ul>
 *     <li>添加初始指令</li>
 *     <li>执行指令</li>
 *     <li>根据状态生成下一指令</li>
 * </ul>
 *
 * @author Lance
 */
public class BattleEmulator {

    /**
     * 进行一场战斗
     *
     * @param battleUnit 战斗单元
     * @return 战报
     */
    public static BattleReport fight(BattleUnit battleUnit) {
        battleUnit.init(); // 初始化
        while (battleUnit.hasNext()) {
            battleUnit.executeNext();
        }
        battleUnit.settle(); // 结算
        return battleUnit.getBattleReport();
    }
}
