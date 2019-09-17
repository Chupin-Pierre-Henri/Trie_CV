package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.view.Component;
import fr.univ_lyon1.info.m1.cv_search.view.Request;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<Component> skills;
    private List<Component> filters;

    Controller() {

    }

    public List<String> receiveRequest(Request req) {
        skills = new ArrayList<Component>();
        filters = new ArrayList<Component>();

        for (Component c : req.getComponents()) {
            if (c.isSkill()){
                skills.add(c);
            } else if (c.isFilter()){
                filters.add(c);
            }
        }
        return this.executeRequest();
    }

    private List<String> executeRequest() {
        List<String> result = new ArrayList<String>();
        // TODO: make it
        return result;
    }

}
