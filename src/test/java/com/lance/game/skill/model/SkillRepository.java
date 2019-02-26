package com.lance.game.skill.model;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Lance
 * @since 2019/2/26 15:24
 */
public class SkillRepository {

    /** skillLevel -> skillGroup */
    private Map<Integer, SkillGroup> skillGroupMap  = new TreeMap<>();
    /** skillId -> skillName */
    private Map<Integer, String>     skillNameCache = new TreeMap<>();

    private static SkillRepository instance;

    private SkillRepository() {
        init();
    }

    private void init() {
        addSkill(Skill.valueOf(1, "星辰之躯", 1, new int[]{}));
        addSkill(Skill.valueOf(2, "奥能渴求", 1, new int[]{}));
        addSkill(Skill.valueOf(3, "原力护甲", 1, new int[]{}));
        addSkill(Skill.valueOf(4, "以太行者", 1, new int[]{}));
        addSkill(Skill.valueOf(5, "充能爆破", 4, new int[]{}));
        addSkill(Skill.valueOf(6, "三元宝珠", 4, new int[]{}));
        addSkill(Skill.valueOf(7, "彼消我长", 4, new int[]{}));
        addSkill(Skill.valueOf(8, "追踪飞弹", 7, new int[]{}));
        addSkill(Skill.valueOf(9, "贼神的复仇", 7, new int[]{}));
        addSkill(Skill.valueOf(10, "灾厄降临", 7, new int[]{}));
        addSkill(Skill.valueOf(11, "瓦解射线", 10, new int[]{}));
        addSkill(Skill.valueOf(12, "原力之波", 10, new int[]{}));
        addSkill(Skill.valueOf(13, "爆炸魔星", 13, new int[]{}));
        addSkill(Skill.valueOf(14, "玻璃大炮", 13, new int[]{}));
        addSkill(Skill.valueOf(15, "幻术师", 13, new int[]{}));
        addSkill(Skill.valueOf(16, "萤火虫", 16, new int[]{}));
        addSkill(Skill.valueOf(17, "镜光魔珠", 16, new int[]{}));
        addSkill(Skill.valueOf(18, "奥术星环", 16, new int[]{}));
        addSkill(Skill.valueOf(19, "钻石体肤", 16, new int[]{}));
        addSkill(Skill.valueOf(20, "时光涌动", 20, new int[]{11}));
        addSkill(Skill.valueOf(21, "反斥", 20, new int[]{12}));
        addSkill(Skill.valueOf(22, "塔拉夏的法理", 20, new int[]{}));
        addSkill(Skill.valueOf(23, "御法者：纯净能量", 20, new int[]{}));
    }

    public void addSkill(Skill skill) {
        skillNameCache.put(skill.getId(), skill.getName());

        if (!skillGroupMap.containsKey(skill.getLevel())) {
            skillGroupMap.put(skill.getLevel(), new SkillGroup());
        }
        skillGroupMap.get(skill.getLevel()).addSkill(skill);
    }


    public static SkillRepository getInstance() {
        if (null == instance) {
            synchronized (SkillRepository.class) {
                if (null == instance) {
                    instance = new SkillRepository();
                }
            }
        }
        return instance;
    }

    public String getSkillName(int id) {
        return skillNameCache.get(id);
    }

    //================================= Getter/Setter =============================
    public Map<Integer, SkillGroup> getSkillGroupMap() {
        return Collections.unmodifiableMap(skillGroupMap);
    }

    public Map<Integer, String> getSkillNameCache() {
        return Collections.unmodifiableMap(skillNameCache);
    }
}
