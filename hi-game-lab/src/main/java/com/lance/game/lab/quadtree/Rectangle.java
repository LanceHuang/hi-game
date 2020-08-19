package com.lance.game.lab.quadtree;

import lombok.Data;

/**
 * 四叉树形状
 *
 * @author Lance
 */
@Data
public class Rectangle {

    private int x;
    private int y;
    private int width;
    private int height;

    /** 重心x坐标 */
    private int centroidX;
    /** 重心y坐标 */
    private int centroidY;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.centroidX = (x + width) / 2;
        this.centroidY = (y + height) / 2;
    }
}
