package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public abstract class StrategyDecoratorTest {
    protected ApplicantBuilder builder;
    protected Applicant a;
    protected List<Skill> skill;
    protected Strategy strat1;

    @Before
    public void setUp() throws Exception {
        skill = new ArrayList<Skill>();
        strat1 = creatNewStrat();
        builder = new ApplicantBuilder("applicant1.yaml");
        a = builder.build();
        strat1.setSkills(skill);
    }

    public abstract Strategy creatNewStrat();

    @Test
    public void setSkills() {
    }

    @Test
    public void respectCriterion() {
        skill.add(new Skill("java"));
        skill.add(new Skill("c"));
        strat1.setSkills(skill);
        assertTrue(strat1.respectCriterion(a));
    }
}