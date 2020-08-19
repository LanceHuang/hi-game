package com.lance.game.lab.quadtree;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

public class QuadTreeTest {

    @Test
    public void getIndex() {
        QuadTree tree = new QuadTree(new Rectangle(0, 0, 300, 200), 0);
        System.out.println(tree.getIndex(new Rectangle(0, 0, 10, 10)));
        System.out.println(tree.getIndex(new Rectangle(150, 100, 10, 10)));
        System.out.println(tree.getIndex(new Rectangle(140, 90, 10, 10)));
        System.out.println(tree.getIndex(new Rectangle(145, 95, 10, 10)));
    }

    @Test
    public void split() {
        QuadTree tree = new QuadTree(new Rectangle(0, 0, 300, 200), 0);
        tree.split();
        System.out.println(tree);
    }

    @Test
    public void insert() {
        QuadTree tree = new QuadTree(new Rectangle(0, 0, 300, 200), 0);
        tree.insert(new Rectangle(0, 0, 10, 10));
        tree.insert(new Rectangle(150, 100, 10, 10));
        tree.insert(new Rectangle(140, 90, 10, 10));
        tree.insert(new Rectangle(145, 95, 10, 10));
        System.out.println(tree);
    }

    @Test
    public void retrieve() {
        Rectangle[] rectArr = {
                new Rectangle(0, 0, 10, 10),
                new Rectangle(150, 100, 10, 10),
                new Rectangle(140, 90, 10, 10),
                new Rectangle(145, 95, 10, 10)};

        QuadTree tree = new QuadTree(new Rectangle(0, 0, 300, 200), 0);
        Arrays.stream(rectArr).forEach(tree::insert);
        Arrays.stream(rectArr).forEach(rect -> {
            System.out.println(rect + " 可能碰撞的对象：");
            tree.retrieve(rect).forEach(System.out::println);
            System.out.println();
        });
    }

    @Test
    public void refresh() {
        Rectangle[] rectArr = {
                new Rectangle(0, 0, 10, 10),
                new Rectangle(150, 100, 10, 10),
                new Rectangle(140, 90, 10, 10),
                new Rectangle(145, 95, 10, 10)};

        QuadTree tree = new QuadTree(new Rectangle(0, 0, 300, 200), 0);
        Arrays.stream(rectArr).forEach(tree::insert);

        // todo 对象移动

        // 刷新
        tree.refresh(tree);
    }
}