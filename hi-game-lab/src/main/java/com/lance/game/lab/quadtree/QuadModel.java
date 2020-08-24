package com.lance.game.lab.quadtree;

import lombok.Getter;
import lombok.Setter;

/**
 * 四叉树中的对象
 *
 * @author Lance
 */
@Getter
@Setter
public class QuadModel {

    /** 对象id */
    private long id;

    /** 对象坐标与形状 */
    private Rectangle rect; // 这里作为成员变量，因为继承的话，会导致以后无法拓展该model

    /** 对象所在子树 */
    private transient QuadTree tree; // 该对象不存储

    public static QuadModel valueOf(long id, Rectangle rect) {
        QuadModel model = new QuadModel();
        model.id = id;
        model.rect = rect;
        return model;
    }

    @Override
    public String toString() {
        return "QuadModel{" +
                "id=" + id +
                ", rect=" + rect +
                '}';
    }
}
