package fr.univ_lyon1.info.m1.cv_search.model.filter;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.List;

public class StrategyMoy extends StrategyDecorator{

    StrategyMoy(String name, int value, Strategy deco) {
        super(name, value, deco);
    }

    @Override
    protected boolean respectCriterion(Applicant a) {
        if( !super.respectCriterion(a) ){
            return false;
        }
        //TODO howto get search Skills box
        int weight = 0;
        List<String> skills = this.getSkills();
        for( String skillName : skills ){
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
