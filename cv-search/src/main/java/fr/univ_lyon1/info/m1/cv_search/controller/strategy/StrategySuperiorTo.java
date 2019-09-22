package fr.univ_lyon1.info.m1.cv_search.controller.strategy;

import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;

import java.util.List;

public class StrategySuperiorTo extends StrategyDecorator {

    StrategySuperiorTo(String name, int value, Strategy deco) {
        super(name, value, deco);
    }

    StrategySuperiorTo(String name, int value) {
        super(name, value, new StrategyComponent());
    }

    @Override
    protected boolean respectCriterion(Applicant a) {
        if (!super.respectCriterion(a)) {
            return false;
        }
        List<Skill> skills = this.getSkills();
        for (Skill skill : skills) {
            String skillName = skill.getName();
            if (a.getSkill(skillName) < this.getValue()) {
                return false;
            }
        }
        return true;
    }
}
