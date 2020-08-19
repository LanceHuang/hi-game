package com.lance.game.lab.quadtree;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        this.nodes = new ArrayList<>(4);
        this.level = level;
        this.bounds = bounds;
    }

    /**
     * 获取物体对应的象限序号 O(1)
     */
    public int getIndex(Rectangle rect) {
        Rectangle bounds = this.bounds;
        boolean onTop = rect.getY() + rect.getHeight() <= bounds.getCentroidY(); // 最下面的边小于重心的y坐标
        boolean onBottom = rect.getY() >= bounds.getCentroidY(); // 最上面的边大于重心的y坐标
        boolean onLeft = rect.getX() + rect.getWidth() <= bounds.getCentroidX(); // 最右边的边小于重心的x坐标
        boolean onRight = rect.getX() >= bounds.getCentroidX(); // 最左边的边大于重心的x坐标

        // 1 0
        // 2 3
        if (onTop) {
            if (onRight) {
                return 0;
            } else if (onLeft) {
                return 1;
            }
        } else if (onBottom) {
            if (onLeft) {
                return 2;
            } else if (onRight) {
                return 3;
            }
        }

        return -1;
    }

    /**
     * 划分象限 O(1)
     */
    public void split() {
        int level = this.level;
        Rectangle bounds = this.bounds;
        int x = bounds.getX();
        int y = bounds.getY();
        int sWidth = bounds.getWidth() / 2;
        int sHeight = bounds.getHeight() / 2;

        this.nodes.add(new QuadTree(new Rectangle(bounds.getCentroidX(), y, sWidth, sHeight), level + 1)); // 右上
        this.nodes.add(new QuadTree(new Rectangle(x, y, sWidth, sHeight), level + 1)); // 左上
        this.nodes.add(new QuadTree(new Rectangle(x, bounds.getCentroidY(), sWidth, sHeight), level + 1)); // 左下
        this.nodes.add(new QuadTree(new Rectangle(bounds.getCentroidX(), bounds.getCentroidY(), sWidth, sHeight), level + 1)); // 右下
    }

    /**
     * 插入对象 O(1)~O(n)
     */
    public void insert(Rectangle rect) {
        List<Rectangle> objs = this.objects;

        // 如果该节点下存在子节点，则尝试插入子节点
        if (this.nodes.size() > 0) {
            int index = this.getIndex(rect);
            if (index != -1) {
                this.nodes.get(index).insert(rect);
                return;
            }
        }

        // 如果当前节点存储的数量超过MAX_OBJECTS，则创建子节点
        if (this.nodes.size() <= 0
                && this.objects.size() >= Constant.MAX_OBJECTS
                && this.level < Constant.MAX_LEVELS) {

            // 创建子节点
            this.split();

            for (int i = objs.size() - 1; i >= 0; i--) { // 倒序遍历
                int index = this.getIndex(objs.get(i));
                if (index != -1) {
                    this.nodes.get(index).insert(objs.remove(i));
                }
            }

            // 插入新添加的节点
            int index = this.getIndex(rect);
            if (index != -1) {
                this.nodes.get(index).insert(rect);
            } else {
                objs.add(rect);
            }
        } else {
            // 否则存储在当前节点下
            objs.add(rect);
        }
    }

    // todo remove Rectangle需要保存tree对象

    /**
     * 检索可能会与物体发生碰撞的所有物体（也可以说是显示形象，这个范围可以再扩大一些，因为屏幕会很大）
     */
    public List<Rectangle> retrieve(Rectangle rect) {
        List<Rectangle> result = new LinkedList<>();

        if (this.nodes.size() > 0) {
            int index = this.getIndex(rect);
            if (index != -1) {
                result = this.nodes.get(index).retrieve(rect);
            } else {
                // todo
            }
        }

        result.addAll(this.objects);
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
                if (index != -1) {
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
