package fr.univ_lyon1.info.m1.cv_search.model.strategy;


public class StrategySuperiorToTest extends StrategyDecoratorTest{

    @Override
    public Strategy creatNewStrat() {
        return new StrategySuperiorTo("superior", 50);
    }

}