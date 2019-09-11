package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;

public abstract class StrategyDecorator extends Strategy {

    private Strategy deco;

    public StrategyDecorator(String name, int value, Strategy decoring) {
        super(name, value);
        this.deco = decoring;
    }

    protected boolean respectCriterion(Applicant a){
        return this.deco.respectCriterion(a);
    }
}
