package com.lance.game.demo.module.player.model;

import com.lance.game.demo.module.attribute.AttributeContainer;
import com.lance.game.demo.module.buff.model.BuffContainer;
import com.lance.game.demo.module.skill.model.SkillContainer;
import com.lance.game.demo.module.storage.model.ItemStorage;
import com.lance.game.orm.annotation.MongoIgnore;

/**
 * 玩家对象
 *
 * @author Lance
 */
public class Player {

    /** 唯一标识 */
    private long   id;
    /** 账号 */
    private String account;
    /** 昵称 */
    private String nickname;
    /** 性别 */
    private int    gender;
    /** 等级 */
    private int    level;
    /** 经验条 */
    private long   exp;

    @MongoIgnore
    private AttributeContainer attributeContainer;
    @MongoIgnore
    private SkillContainer     skillContainer;
    @MongoIgnore
    private BuffContainer      buffContainer;

    // todo 背包 Ctrl+I 打开背包，哈哈哈（冒险岛、彩虹岛）
    // 1. 客户端设计快捷键？并且支持保存配置
    // 2. 支持整理？背包格子有index（数据库，index -> 道具）
    // todo 装备栏

    // todo 暂时不考虑多种背包的情况，还有仓库的情况
    @MongoIgnore
    private ItemStorage itemStorage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public AttributeContainer getAttributeContainer() {
        return attributeContainer;
    }

    public void setAttributeContainer(AttributeContainer attributeContainer) {
        this.attributeContainer = attributeContainer;
    }

    public SkillContainer getSkillContainer() {
        return skillContainer;
    }

    public void setSkillContainer(SkillContainer skillContainer) {
        this.skillContainer = skillContainer;
    }

    public BuffContainer getBuffContainer() {
        return buffContainer;
    }

    public void setBuffContainer(BuffContainer buffContainer) {
        this.buffContainer = buffContainer;
    }

    public ItemStorage getItemStorage() {
        return itemStorage;
    }

    public void setItemStorage(ItemStorage itemStorage) {
        this.itemStorage = itemStorage;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", level=" + level +
                ", exp=" + exp +
                '}';
    }
}
