package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;

import java.util.HashMap;

public abstract class Strategy {
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
}
