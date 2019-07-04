package com.lance.game.module.player.model;

import com.lance.game.module.attribute.model.AttributeContainer;
import com.lance.game.module.buff.model.BuffContainer;
import com.lance.game.module.skill.model.SkillContainer;
import lombok.Data;

/**
 * @author Lance
 * @since 2019/7/2 17:25
 */
@Data
public class Player {

    private AttributeContainer attributeContainer;
    private SkillContainer     skillContainer;
    private BuffContainer      buffContainer;

}
