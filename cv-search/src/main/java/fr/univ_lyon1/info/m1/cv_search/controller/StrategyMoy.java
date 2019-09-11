package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class StrategyMoy extends Strategy{

    StrategyMoy(String name, int value) {
        super(name, value);
    }

    @Override
    protected boolean respectCriterion(Applicant a) {
        //TODO howto get search Skills box
        int weight = 0;
        ObservableList<Node> skills = searchSkillsBox.getChildren();
        for( Node skill : skills ){
            String skillName = ((Button) skill).getText();
            weight += a.getSkill(skillName);
        }
        if( skills.size() != 0 ){
            int moy = weight/skills.size();
            if( moy > this.getValue() )
                this.result.put(a,moy);
            else{
                return false;
            }
        }else{
            this.result.put(a,0);
        }
        return true;
    }
}
