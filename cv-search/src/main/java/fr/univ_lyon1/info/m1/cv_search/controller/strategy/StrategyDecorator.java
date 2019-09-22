package fr.univ_lyon1.info.m1.cv_search.controller.strategy;

import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;

import java.util.List;

public abstract class StrategyDecorator extends Strategy {

    private Strategy deco;

    public StrategyDecorator(String name, int value, Strategy decoring) {
        super(name, value);
        this.deco = decoring;
    }

    @Override
    public void setSkills(List<Skill> skills) {
        super.setSkills(skills);
        deco.setSkills(skills);
    }

    @Override
    protected boolean respectCriterion(Applicant a) {
        return this.deco.respectCriterion(a);
    }
}
