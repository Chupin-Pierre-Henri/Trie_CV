package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;

public class StrategyComponent extends Strategy {

    StrategyComponent() {
        super("base", 0);
    }

    @Override
    protected boolean respectCriterion(Applicant a) {
        return true;
    }
}
