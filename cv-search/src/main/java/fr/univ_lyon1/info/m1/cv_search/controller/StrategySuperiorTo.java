package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class StrategySuperiorTo extends Strategy {

    StrategySuperiorTo(String name, int value) {
        super(name, value);
    }


    private boolean respectCriterion(Applicant a){
        //TODO howto get search Skills box
        int weight = 0;
        ObservableList<Node> skills = searchSkillsBox.getChildren();
        for (Node skill : skills ) {
            String skillName = ((Button) skill).getText();
            weight += a.getSkill(skillName);
            if (a.getSkill(skillName) < this.getValue()) {
                return false;
            }
        }
        if( skills.size() != 0){
            this.result.put(a,weight/skills.size());
        }else{
            this.result.put(a,0);
        }
        return true;
    }
}
