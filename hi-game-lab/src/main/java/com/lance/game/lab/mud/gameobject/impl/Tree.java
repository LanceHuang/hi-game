package com.lance.game.lab.mud.gameobject.impl;

import com.lance.game.lab.mud.gameobject.GameObject;

/**
 * 树木
 *
 * @author Lance
 * @since 2021/9/7
 */
public class Tree extends GameObject {

    /** 是否可破坏 */
    private boolean canDestroy;


    public Tree(long id) {
        super(id);
    }
}
