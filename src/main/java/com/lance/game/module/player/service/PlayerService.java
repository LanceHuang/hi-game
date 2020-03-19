package com.lance.game.module.player.service;

import com.lance.game.module.attribute.model.AttributeContainer;
import com.lance.game.module.buff.model.BuffContainer;
import com.lance.game.module.player.model.Player;
import com.lance.game.module.skill.model.SkillContainer;
import com.lance.game.module.storage.model.ItemStorage;
import org.springframework.stereotype.Service;

/**
 * @author Lance
 * @since 2019/9/2 15:43
 */
@Service
public class PlayerService implements IPlayerService {

    @Override
    public Player getPlayer(long id) {
        // todo
        Player player = new Player();
        player.setId(id);
        player.setItemStorage(new ItemStorage());
        player.setAttributeContainer(new AttributeContainer());
        player.setSkillContainer(SkillContainer.valueOf());
        player.setBuffContainer(new BuffContainer());
        return player;
    }
}
