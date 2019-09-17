package fr.univ_lyon1.info.m1.cv_search.model.filter;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.view.Skill;

import java.util.HashMap;
import java.util.List;

public abstract class Strategy {
    protected List<Skill> skills;
    private String name;
    private int value;
    HashMap<Applicant, Integer> result;

    Strategy(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Filtering the list of applicant by a criterion
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

    protected abstract boolean respectCriterion(Applicant a);

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public HashMap<Applicant, Integer> getResult() {
        return result;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
