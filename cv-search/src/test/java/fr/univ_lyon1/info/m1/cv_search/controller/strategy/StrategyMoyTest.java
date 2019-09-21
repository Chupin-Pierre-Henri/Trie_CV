package fr.univ_lyon1.info.m1.cv_search.controller.strategy;


public class StrategyMoyTest extends StrategyDecoratorTest {

    @Override
    public Strategy creatNewStrat() {
        return new StrategyMoy("average", 50);
    }
}