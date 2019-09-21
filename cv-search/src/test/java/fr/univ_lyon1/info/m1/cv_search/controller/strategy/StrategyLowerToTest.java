package fr.univ_lyon1.info.m1.cv_search.controller.strategy;


public class StrategyLowerToTest extends StrategyDecoratorTest {

    @Override
    public Strategy creatNewStrat() {
        return new StrategyLowerTo("lower", 90);
    }
}