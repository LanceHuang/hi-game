package com.lance.game.lab.quadtree;

/**
 * @author Lance
 */
public interface Constant {

    /** 每个节点（象限）所能包含物体的最大数量 */
    int MAX_OBJECTS = 1;
    /** 四叉树的最大深度 */
    int MAX_LEVELS  = 5;

    /** 无法索引 */
    int UNABLE_TO_INDEX = -1;
    /** 第一象限 */
    int TOP_RIGHT       = 0;
    /** 第二象限 */
    int TOP_LEFT        = 1;
    /** 第三象限 */
    int BOTTOM_LEFT     = 2;
    /** 第四象限 */
    int BOTTOM_RIGHT    = 3;
}
