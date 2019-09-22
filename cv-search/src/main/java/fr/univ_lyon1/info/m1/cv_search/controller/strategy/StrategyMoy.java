package fr.univ_lyon1.info.m1.cv_search.controller.strategy;

import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;

import java.util.List;
import java.util.TreeMap;

public class StrategyMoy extends StrategyDecorator {
    private TreeMap<Integer,ApplicantList> sortedMap;

    public StrategyMoy(String name, int value, Strategy deco) {
        super(name, value, deco);
        this.sortedMap = new TreeMap<Integer,ApplicantList>();

    }

    StrategyMoy(String name, int value) {
        this(name, value, new StrategyComponent());
    }

    public TreeMap<Integer,ApplicantList> getTreeMap() {
        return sortedMap;
    }

    @Override
    protected boolean respectCriterion(Applicant a) {
        if (!super.respectCriterion(a)) {
            return false;
        }
        int weight = 0;
        List<Skill> skills = this.getSkills();
        for (Skill skill : skills) {
            String skillName = skill.getName();
            weight += a.getSkill(skillName);
        }
        if (skills.size() != 0) {
            int moy = weight / skills.size();
            if (moy > this.getValue()) {
                this.addApplicant(a, moy);
            } else {
                return false;
            }
        } else {
            this.addApplicant(a, 0);
        }
        return true;
    }

    private void addApplicant(Applicant a, int i) {
        if( ! sortedMap.containsKey(i)){
            ApplicantList list = new ApplicantList();
            sortedMap.put(i,list);
        }
        sortedMap.get(i).add(a);
    }

    public ApplicantList sort(){
        ApplicantList result = new ApplicantList();
        for (ApplicantList list : sortedMap.descendingMap().values()) {
            for (Applicant a : list) {
                result.add(a);
            }
        }
        return result;
    }
}
