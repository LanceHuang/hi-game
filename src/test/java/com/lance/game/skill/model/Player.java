package com.lance.game.skill.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Lance
 * @since 2019/2/26 15:24
 */
public class Player {

    private int                   level         = 1;
    /** skillGroupLevel -> skillId */
    private Map<Integer, Integer> skillSelected = new HashMap<>();

    public void showCandidateSkills() {
        SkillRepository repository = SkillRepository.getInstance();
        Map<Integer, SkillGroup> skillGroupMap = repository.getSkillGroupMap();


        SkillGroup skillGroup = null;
        int minLevel = Integer.MAX_VALUE;
        for (Map.Entry<Integer, SkillGroup> entry : skillGroupMap.entrySet()) {
            if (level < entry.getKey()) {
                continue;
            }
            if (skillSelected.containsKey(entry.getKey())) {
                continue;
            }

            if (entry.getKey() <= minLevel) {
                minLevel = entry.getKey();
                skillGroup = entry.getValue();
            }
        }

        if (null == skillGroup) {
            System.out.println("Nothing can be selected");
            return;
        }


        List<Skill> candidate = new LinkedList<>();
        for (Skill skill : skillGroup.getSkillMap().values()) {
            boolean f = true;
            for (int skillId : skill.getAhead()) {
                f = f & skillSelected.values().contains(skillId);
            }

            if (f) {
                candidate.add(skill);
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("=============================================\n");
        for (Skill skill : candidate) {
            builder.append(skill.toString());
            builder.append('\n');
        }
        builder.append("=============================================\n");
        System.out.println(builder.toString());
    }

    public void select(int level, int id) {
        skillSelected.put(level, id);
    }

    public void showSkills() {
        SkillRepository repository = SkillRepository.getInstance();

        StringBuilder builder = new StringBuilder();
        builder.append("=============================================\n");
        for (Map.Entry<Integer, Integer> entry : skillSelected.entrySet()) {
            builder.append(String.format("level:%d id:%d name:%s\n",
                    entry.getKey(), entry.getValue(), repository.getSkillName(entry.getValue())));
        }
        builder.append("=============================================\n");
        System.out.println(builder.toString());
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Map<Integer, Integer> getSkillSelected() {
        return skillSelected;
    }

    public void setSkillSelected(Map<Integer, Integer> skillSelected) {
        this.skillSelected = skillSelected;
    }
}
