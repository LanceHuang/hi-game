package com.lance.game.demo.module.emulator;

import com.lance.game.demo.module.attribute.AttributeType;
import lombok.Data;

import java.util.Map;

/**
 * 生物单元
 *
 * @author Lance
 */
@Data
public class CreatureUnit {

    private long id;

    private State state;

    /**
     * 还不确定是用map，还是成员变量。用map则需要额外的get操作
     */
    private Map<AttributeType, Long> attributes;

    private long maxHp;

    private long atk;

    private long hp;

    public CreatureUnit(long id) {
        this.id = id;
        this.state = State.NORMAL;
    }

    /**
     * 判断该单元是否已阵亡
     */
    public boolean isDead() {
        return this.hp <= 0L;
    }

    /**
     * 回血
     */
    public void recoverHp(long recover) {
        this.hp = Math.min(this.maxHp, this.hp + recover);
    }

    /**
     * 扣血
     */
    public void reduceHp(long reduce) {
        this.hp = Math.max(0L, this.hp - reduce);
    }

    /**
     * 获取属性值
     *
     * @param type 属性类型
     */
    public long getAttributeValue(AttributeType type) {
        Long value = this.attributes.get(type);
        return value == null ? 0L : value;
    }

}
