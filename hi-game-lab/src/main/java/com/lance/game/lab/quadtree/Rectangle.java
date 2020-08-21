package com.lance.game.lab.quadtree;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 矩形
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

    /**
     * 将矩形按四象限切割成多个小矩形，前面已经能确定，bounds必然包含当前对象（切割会产生大量小对象）
     */
    public List<Rectangle> carve(Rectangle bounds) {
        int centroidX = bounds.getCentroidX(); // 重心x坐标
        int centroidY = bounds.getCentroidY(); // 重心y坐标

        boolean onTop = y + height <= centroidY; // 最下面的边小于重心的y坐标
        boolean onBottom = y >= centroidY; // 最上面的边大于重心的y坐标
        boolean onLeft = x + width <= centroidX; // 最右边的边小于重心的x坐标
        boolean onRight = x >= centroidX; // 最左边的边大于重心的x坐标

        // 只有五种可能
        List<Rectangle> result = new LinkedList<>();
        if (onTop && !onBottom) { // 一二象限
            result.add(new Rectangle(x, y, centroidX - x, height));
            result.add(new Rectangle(centroidX, y, width - (centroidX - x), height));
        } else if (!onTop && onBottom) { // 三四象限
            result.add(new Rectangle(x, y, centroidX - x, height));
            result.add(new Rectangle(centroidX, y, width - (centroidX - x), height));
        } else if (onLeft && !onRight) { // 二三象限
            result.add(new Rectangle(x, y, width, centroidY - y));
            result.add(new Rectangle(x, centroidY, width, height - (centroidY - y)));
        } else if (!onLeft && onRight) { // 一四象限
            result.add(new Rectangle(x, y, width, centroidY - y));
            result.add(new Rectangle(x, centroidY, width, height - (centroidY - y)));
        } else { // 一二三四象限
            result.add(new Rectangle(x, y, centroidX - x, centroidY - y));
            result.add(new Rectangle(centroidX, y, width - (centroidX - x), centroidY - y));
            result.add(new Rectangle(x, centroidY, centroidX - x, height - (centroidY - y)));
            result.add(new Rectangle(centroidX, centroidY, width - (centroidX - x), height - (centroidY - y)));
        }
        return result;
    }

}
