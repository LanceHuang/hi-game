package com.lance.game.demo.module.item.config;

/**
 * 道具配置
 *
 * @author Lance
 */
public class ItemConfig {

    private int    id;
    private String name;
    private int    type;

    // todo 属性

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ItemConfig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
