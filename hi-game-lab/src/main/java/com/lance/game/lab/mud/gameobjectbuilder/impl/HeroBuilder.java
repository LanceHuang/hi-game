package com.lance.game.lab.mud.gameobjectbuilder.impl;

import com.lance.game.lab.mud.gameobjectbuilder.GameObjectBuilder;
import com.lance.game.lab.mud.gameobject.impl.Hero;

/**
 * 英雄构造器
 *
 * @author Lance
 * @since 2021/9/7
 */
public class HeroBuilder extends GameObjectBuilder<Hero> {

    @Override
    public Hero build(int configId, long id) {
        Hero hero = new Hero(id);
        return hero;
    }
}
