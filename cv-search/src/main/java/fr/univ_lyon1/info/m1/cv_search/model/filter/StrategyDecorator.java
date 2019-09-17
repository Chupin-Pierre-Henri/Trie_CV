package fr.univ_lyon1.info.m1.cv_search.model.filter;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.view.Skill;

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

    protected boolean respectCriterion(Applicant a) {
        return this.deco.respectCriterion(a);
    }
}
