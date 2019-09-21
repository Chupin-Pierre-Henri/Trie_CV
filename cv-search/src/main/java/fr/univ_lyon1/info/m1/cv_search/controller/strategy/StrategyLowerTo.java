package fr.univ_lyon1.info.m1.cv_search.controller.strategy;

import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;

import java.util.List;

public class StrategyLowerTo extends StrategyDecorator {

    StrategyLowerTo(String name, int value, Strategy deco) {
        super(name, value, deco);
    }

    StrategyLowerTo(String name, int value) {
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
            if (a.getSkill(skillName) > this.getValue()) {
                return false;
            }
        }
        if (skills.size() != 0) {
            this.result.put(a, weight / skills.size());
        } else {
            this.result.put(a, 0);
        }
        return true;
    }
}
