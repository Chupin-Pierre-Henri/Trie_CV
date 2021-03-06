package fr.univ_lyon1.info.m1.cv_search.controller.strategy;

import fr.univ_lyon1.info.m1.cv_search.controller.component.Filter;
import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;

import java.util.List;

public class StrategyBuilder {

    private Strategy strategy;

    /**
     * build a strategy depending of filters and skills.
     * @param filters list of Filter
     * @param skills list of Skill
     */
    public StrategyBuilder(List<Filter> filters, List<Skill> skills) {
        // construct the base Strategy
        if (filters.size() >= 1) {
            strategy = constructBaseStrategy(filters.get(0));
        } else {
            strategy = new StrategyComponent();
        }
        // decorate all filter with a strategy
        for (int i = 1; i < filters.size(); i++) {
            strategy = constructStrategy(filters.get(i),strategy);
        }
        strategy.setSkills(skills);
    }

    /**
     * decorate strategy with base strategy component.
     * @param filter the filter of the strategy to apply
     * @return the strategy to apply
     */
    private Strategy constructBaseStrategy(Filter filter) {
        return constructStrategy(filter, new StrategyComponent());
    }

    /**
     * decorate strategy with decoring parameter.
     * @param filter the filter of the strategy to apply
     * @param decoring the decorator to apply to the filter strategy
     * @return the strategy to apply
     */
    private Strategy constructStrategy(Filter filter, Strategy decoring) {
        Strategy ret;
        String name = filter.getName();
        int value = filter.getValue();
        // create a strategy depending to the Filter
        switch (name) {
            case "superior":
                ret = new StrategySuperiorTo(name,value,decoring);
                break;
            case "average":
                ret = new StrategyMoy(name,value,decoring);
                break;
            case "lower":
                ret = new StrategyLowerTo(name,value,decoring);
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
