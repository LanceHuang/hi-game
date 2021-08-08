package com.lance.game.lab.event.condition;

import com.lance.game.lab.event.Event;
import org.mvel2.MVEL;

/**
 * MVEL表达式
 *
 * @author Lance
 * @since 2021/8/9
 */
public class MVELEventCondition implements EventCondition {

    private String condition;

    public MVELEventCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public boolean verify(Event event) {
        if (this.condition != null && !this.condition.isEmpty()) {
            return MVEL.eval(this.condition, event, Boolean.class);
        }
        return true;
    }
}
