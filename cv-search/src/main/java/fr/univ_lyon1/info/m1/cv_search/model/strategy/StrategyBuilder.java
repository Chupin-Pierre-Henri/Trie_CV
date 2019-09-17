package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.controller.component.Filter;
import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;

import java.util.List;

public class StrategyBuilder {

    private Strategy strategy;

    public StrategyBuilder(List<Filter> filters, List<Skill> skills) {
        if (filters.size() > 1) {
            strategy = constructBaseStrategy(filters.get(0));
        } else {
            strategy = new StrategyComponent();
        }
        for (int i = 0; i < filters.size(); i++) {
            strategy = constructStrategy(filters.get(i),strategy);
        }
        strategy.setSkills(skills);
    }

    private Strategy constructBaseStrategy(Filter filter){
        return constructStrategy(filter, new StrategyComponent());
    }

    private Strategy constructStrategy(Filter filter, Strategy decoring) {
        Strategy ret;
        String name = filter.getName();
        int value = filter.getValue();
        switch (name) {
            case "superior":
                ret = new StrategySuperiorTo(name,value,decoring);
                break;
//            TODO finish that
//            case "inferior":
//                ret = new StrategyInferiorTo(name,value,decoring);
//                break;
            case "average":
                ret = new StrategyMoy(name,value,decoring);
                break;
            default:
                ret = decoring;
        }
        return ret;
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
