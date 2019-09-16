package fr.univ_lyon1.info.m1.cv_search.model.filter;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.List;

public class StrategySuperiorTo extends StrategyDecorator {

    StrategySuperiorTo(String name, int value, Strategy deco) {
        super(name, value, deco);
    }

    StrategySuperiorTo(String name, int value) {
        super(name, value, new StrategyComponent());
    }

    protected boolean respectCriterion(Applicant a){
        int weight = 0;
        List<Skill> skills = this.getSkills();
        for (Skill skill : skills ) {
            String skillName = skill.getName();
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
