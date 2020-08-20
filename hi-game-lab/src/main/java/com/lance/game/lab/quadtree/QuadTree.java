package com.lance.game.lab.quadtree;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.lance.game.lab.quadtree.Constant.*;

/**
 * 四叉树。https://blog.csdn.net/qq276592716/article/details/45999831
 * 好像解决不了魔兽世界那种大地图
 *
 * @author Lance
 */
@Data
public class QuadTree {

    /** 当前节点下的对象 */
    private List<Rectangle> objects;

    /** 子节点 */
    private List<QuadTree> nodes;

    /** 树层级 */
    private int level;

    /** 树坐标范围 */
    private Rectangle bounds;

    public QuadTree(Rectangle bounds, int level) {
        this.objects = new ArrayList<>();
        this.nodes = new ArrayList<>(4); // 只有四个象限
        this.level = level;
        this.bounds = bounds;
    }

    /**
     * 获取物体对应的象限序号 O(1)
     */
    public int getIndex(Rectangle rect) {
        int centroidX = this.bounds.getCentroidX(); // 重心x坐标
        int centroidY = this.bounds.getCentroidY(); // 重心y坐标

        boolean onTop = rect.getY() + rect.getHeight() <= centroidY; // 最下面的边小于重心的y坐标
        boolean onBottom = rect.getY() >= centroidY; // 最上面的边大于重心的y坐标
        boolean onLeft = rect.getX() + rect.getWidth() <= centroidX; // 最右边的边小于重心的x坐标
        boolean onRight = rect.getX() >= centroidX; // 最左边的边大于重心的x坐标

        // 1 0
        // 2 3
        if (onTop && onRight) { // 右上
            return TOP_RIGHT;
        } else if (onTop && onLeft) { // 左上
            return TOP_LEFT;
        } else if (onBottom && onLeft) { // 左下
            return BOTTOM_LEFT;
        } else if (onBottom && onRight) { // 右下
            return BOTTOM_RIGHT;
        }
        return UNABLE_TO_INDEX;
    }

    /**
     * 划分象限 O(1)
     */
    public void split() {
        int centroidX = this.bounds.getCentroidX(); // 重心x坐标
        int centroidY = this.bounds.getCentroidY(); // 重心y坐标
        int sLevel = this.level + 1; // 子节点层级

        int x = this.bounds.getX();
        int y = this.bounds.getY();
        int sWidth = this.bounds.getWidth() / 2;
        int sHeight = this.bounds.getHeight() / 2;

        this.nodes.add(new QuadTree(new Rectangle(centroidX, y, sWidth, sHeight), sLevel)); // 右上
        this.nodes.add(new QuadTree(new Rectangle(x, y, sWidth, sHeight), sLevel)); // 左上
        this.nodes.add(new QuadTree(new Rectangle(x, centroidY, sWidth, sHeight), sLevel)); // 左下
        this.nodes.add(new QuadTree(new Rectangle(centroidX, centroidY, sWidth, sHeight), sLevel)); // 右下
    }

    /**
     * 插入对象
     */
    public void insert(Rectangle rect) {
        // 如果该节点下存在子节点，则尝试插入子节点
        if (this.nodes.size() > 0) {
            int index = this.getIndex(rect);
            if (index != UNABLE_TO_INDEX) {
                this.nodes.get(index).insert(rect);
                return;
            }
        }

        // 如果当前节点存储的数量超过MAX_OBJECTS，则创建子节点
        if (this.nodes.size() <= 0 && this.objects.size() >= MAX_OBJECTS && this.level < MAX_LEVELS) {

            // 创建子节点
            this.split();

            for (int i = this.objects.size() - 1; i >= 0; i--) { // 倒序遍历
                int index = this.getIndex(this.objects.get(i));
                if (index != UNABLE_TO_INDEX) {
                    this.nodes.get(index).insert(this.objects.remove(i));
                }
            }

            // 插入新添加的节点
            int index = this.getIndex(rect);
            if (index != UNABLE_TO_INDEX) {
                this.nodes.get(index).insert(rect);
            } else {
                this.objects.add(rect);
            }
        } else {
            // 否则存储在当前节点下
            this.objects.add(rect);
        }
    }

    // todo remove Rectangle需要保存tree对象

    /**
     * 检索可能会与物体发生碰撞的所有物体（同象限）
     */
    public List<Rectangle> retrieve(Rectangle rect) {
        if (this.nodes.size() <= 0) { // 没有子节点，则直接返回当前节点的对象
            return new LinkedList<>(this.objects);
        }

        List<Rectangle> result = new LinkedList<>();
        int index = this.getIndex(rect);
        if (index != UNABLE_TO_INDEX) { // 返回子节点的对象
            result = this.nodes.get(index).retrieve(rect);
        } else { // 分块，并返回分块所在子节点的对象
            // todo
        }
        result.addAll(this.objects); // 当前节点的对象
        return result;
    }

    // todo 移动时怎么处理？

    /**
     * 动态更新
     *
     * @param root 顶层节点
     */
    public void refresh(QuadTree root) { // todo 移动时才更新
        root = (root == null ? this : root);
        List<Rectangle> objs = this.objects;

        for (int i = objs.size() - 1; i >= 0; i--) {
            Rectangle rect = objs.get(i);

            if (!isInner(rect, this.bounds)) { // 如果矩形不属于该象限，则将该矩形重新插入
                if (this != root) { // 同一层没必要再次插入
                    root.insert(objs.remove(i));
                }
            } else if (this.nodes.size() > 0) { // 如果矩形属于该象限 且 该象限具有子象限，则尝试将该矩形插入到子象限中
                int index = this.getIndex(rect);
                if (index != UNABLE_TO_INDEX) {
                    this.nodes.get(index).insert(objs.remove(i));
                }
            }
        }

        // 递归刷新子象限
        for (QuadTree node : this.nodes) {
            node.refresh(root);
        }
    }

    /**
     * 判断矩形时是否在象限范围内
     */
    public static boolean isInner(Rectangle rect, Rectangle bounds) {
        // rect的起点必须在bounds的右下方
        // rect的终点必须在bounds的左上方
        return rect.getX() >= bounds.getX()
                && rect.getX() + rect.getWidth() <= bounds.getX() + bounds.getWidth()
                && rect.getY() >= bounds.getY()
                && rect.getY() + rect.getHeight() <= bounds.getY() + bounds.getHeight();
    }

}
