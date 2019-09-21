package fr.univ_lyon1.info.m1.cv_search.controller.strategy;

import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;

import java.util.HashMap;
import java.util.List;

public abstract class Strategy {
    protected List<Skill> skills;
    private String name;
    private int value;

    Strategy(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Filtering the list of applicant by a criterion.
     * @param applicants a list of applicant
     * @return the list of applicant that respect criterion
     */
    public ApplicantList filter(ApplicantList applicants) {
        ApplicantList result = new ApplicantList();
        for (Applicant a : applicants) {
            if (respectCriterion(a)) {
                result.add(a);
            }
        }
        return result;
    }

    /**
     * Chech if the applicant meets the criteria of the strategy.
     * @param a the applicant test
     * @return true if applicant respect the criteria  and false esle
     */
    protected abstract boolean respectCriterion(Applicant a);

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
