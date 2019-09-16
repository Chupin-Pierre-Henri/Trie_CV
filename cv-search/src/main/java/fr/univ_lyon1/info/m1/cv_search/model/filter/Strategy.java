package fr.univ_lyon1.info.m1.cv_search.model.filter;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;

import java.util.HashMap;
import java.util.List;

public abstract class Strategy {
    protected List<String> skills;
    private String name;
    private int value;
    HashMap<Applicant,Integer> result;

    Strategy(String name, int value){
        this.name = name;
        this.value = value;
    }

    public ApplicantList filter(ApplicantList applicants) {
        ApplicantList result = new ApplicantList();
        for ( Applicant a :  applicants) {
            if (respectCriterion(a)){
                result.add(a);
            }
        }
        return result;
    }

    protected abstract boolean respectCriterion(Applicant a);

    public String getName(){
        return name;
    }

    public int getValue() {
        return value;
    }

    public HashMap<Applicant,Integer> getResult(){
        return result;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
