package com.lance.game.lab.quadtree;

import lombok.Data;

/**
 * 四叉树中的对象
 *
 * @author Lance
 */
@Data
public class QuadModel {

    /** 对象id */
    private long id;

    /** 对象坐标与形状 */
    private Rectangle rect; // 这里作为成员变量，因为继承的话，会导致以后无法拓展该model

    public static QuadModel valueOf(long id, Rectangle rect) {
        QuadModel model = new QuadModel();
        model.id = id;
        model.rect = rect;
        return model;
    }
}
