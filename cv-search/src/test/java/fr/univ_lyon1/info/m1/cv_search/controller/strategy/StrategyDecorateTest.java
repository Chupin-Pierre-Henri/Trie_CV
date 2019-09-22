package fr.univ_lyon1.info.m1.cv_search.controller.strategy;

public class StrategyDecorateTest extends StrategyDecoratorTest{

    @Override
    public Strategy creatNewStrat() {
        return new StrategyMoy("average", 60, new StrategySuperiorTo("superior", 50));
    }

}