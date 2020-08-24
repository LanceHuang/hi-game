package com.lance.game.lab.quadtree;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class QuadTreeTest {

    private List<QuadModel> models;

    @Before
    public void before() {
        models = new LinkedList<>();
        models.add(QuadModel.valueOf(1L, new Rectangle(0, 0, 10, 10))); // 第二象限
        models.add(QuadModel.valueOf(2L, new Rectangle(150, 100, 10, 10))); // 第四象限
        models.add(QuadModel.valueOf(3L, new Rectangle(140, 90, 10, 10))); // 第二象限
        models.add(QuadModel.valueOf(4L, new Rectangle(145, 95, 10, 10))); // 第一二三四象限
    }

    @Test
    public void getIndex() {
        QuadTree tree = new QuadTree(new Rectangle(0, 0, 300, 200), 0);
        models.forEach(model -> System.out.println(tree.getIndex(model)));
    }

    @Test
    public void split() {
        QuadTree tree = new QuadTree(new Rectangle(0, 0, 300, 200), 0);
        tree.printRoot();
        System.out.println("==============");
        tree.split();
        tree.printRoot();
    }

    @Test
    public void insert() {
        QuadTree tree = new QuadTree(new Rectangle(0, 0, 300, 200), 0);
        tree.printRoot();
        System.out.println("==============");
        models.forEach(tree::insert);
        tree.printRoot();
    }

    @Test
    public void retrieve() { // todo A能碰撞到D，D碰撞不到A
        QuadTree tree = new QuadTree(new Rectangle(0, 0, 300, 200), 0);
        models.forEach(tree::insert);
        models.forEach(model -> {
            System.out.println(model + " 可能碰撞的对象：");
            List<QuadModel> retrieveRect = tree.retrieve(model);
            retrieveRect.remove(model); // 删除当前对象
            retrieveRect.forEach(System.out::println);
            System.out.println();
        });

        tree.printRoot();
    }

    @Test
    public void remove() {
        QuadTree tree = new QuadTree(new Rectangle(0, 0, 300, 200), 0);
        models.forEach(tree::insert);
        tree.printRoot();
        System.out.println("==============");
        tree.remove(models.get(2));
        tree.printRoot();
    }

    @Test
    public void refresh() {
        QuadTree tree = new QuadTree(new Rectangle(0, 0, 300, 200), 0);
        models.forEach(tree::insert);

        // todo 对象移动

        // 刷新
        tree.refresh(tree);
    }
}