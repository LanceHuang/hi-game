package com.lance.game.module.player.entity;

import lombok.Data;

/**
 * Player info, like attribute, skill, cooling down, buff
 *
 * @author Lance
 * @since 2018/11/10 14:20
 */
@Data
public class Player {

    private String nickname;
    /** @see Gender */
    private int    gender;
    private int    level;

    public static enum Gender {
        MAN(1),
        WOMAN(2);

        private int value;

        Gender(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
