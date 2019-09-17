package fr.univ_lyon1.info.m1.cv_search.model.strategy;


public class StrategyLowerToTest extends StrategyDecoratorTest {

    @Override
    public Strategy creatNewStrat() {
        return new StrategyMoy("average", 50);
    }
}