package fr.univ_lyon1.info.m1.cv_search.view;

public class Skill implements Component {

    private String name;

    public Skill(String name) {
        this.name = name;
    }

    @Override
    public boolean isFilter() {
        return false;
    }

    @Override
    public boolean isSkill() {
        return true;
    }


}