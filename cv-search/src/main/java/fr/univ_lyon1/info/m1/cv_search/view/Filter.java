package fr.univ_lyon1.info.m1.cv_search.view;

public class Filter implements Component {
    private String name;
    private int value;

    public Filter(String name, int value) {
        this.name = name;
        this.value = value;
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
