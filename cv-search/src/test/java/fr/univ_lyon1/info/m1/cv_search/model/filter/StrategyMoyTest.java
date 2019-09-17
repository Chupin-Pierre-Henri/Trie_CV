package fr.univ_lyon1.info.m1.cv_search.model.filter;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.view.Skill;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StrategyMoyTest extends StrategyDecoratorTest {

    @Override
    public Strategy creatNewStrat() {
        return new StrategyMoy("average", 50);
    }
}