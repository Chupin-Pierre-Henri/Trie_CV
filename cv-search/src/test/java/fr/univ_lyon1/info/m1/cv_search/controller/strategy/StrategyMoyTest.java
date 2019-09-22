package fr.univ_lyon1.info.m1.cv_search.controller.strategy;


import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantListBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StrategyMoyTest extends StrategyDecoratorTest {


    @Override
    public Strategy creatNewStrat() {
        return new StrategyMoy("average", 50);
    }

    @Test
    public void sortTest(){
        ApplicantList listApplicants = new ApplicantListBuilder(new File(".")).build();
        List<Skill> skill = new ArrayList<Skill>();
        skill.add(new Skill("c"));
        StrategyMoy strategyMoy = new StrategyMoy("sort", 0);
        strategyMoy.setSkills(skill);
        strategyMoy.filter(listApplicants);
        listApplicants = strategyMoy.sort();
        assertEquals(listApplicants.getNamesOfApplicants().get(0),"John Smith");
        assertEquals(listApplicants.getNamesOfApplicants().get(1),"Foo Bar");
    }
}