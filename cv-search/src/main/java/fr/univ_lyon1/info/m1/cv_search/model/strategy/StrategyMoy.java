package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;

import java.util.List;

public class StrategyMoy extends StrategyDecorator {

    StrategyMoy(String name, int value, Strategy deco) {
        super(name, value, deco);
    }

    StrategyMoy(String name, int value) {
        super(name, value, new StrategyComponent());
    }

    @Override
    protected boolean respectCriterion(Applicant a) {
        if (!super.respectCriterion(a)) {
            return false;
        }
        int weight = 0;
        List<Skill> skills = this.getSkills();
        for (Skill skill : skills) {
            String skillName = skill.getName();
            weight += a.getSkill(skillName);
        }
        if (skills.size() != 0) {
            int moy = weight / skills.size();
            if (moy > this.getValue()) {
                this.result.put(a, moy);
            } else {
                return false;
            }
        } else {
            this.result.put(a, 0);
        }
        return true;
    }
}
