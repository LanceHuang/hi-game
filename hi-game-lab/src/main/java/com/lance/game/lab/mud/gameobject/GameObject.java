package com.lance.game.lab.mud.gameobject;

/**
 * 游戏对象
 *
 * @author Lance
 * @since 2021/9/7
 */
public abstract class GameObject {

    /** 对象标识 */
    private final long id;

    public GameObject(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
