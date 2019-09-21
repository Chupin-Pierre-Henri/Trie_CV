package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.controller.component.Filter;
import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;

import java.util.ArrayList;
import java.util.List;

public class Request {
    protected String requestType;
    protected List<Skill> skills;
    protected List<Filter> filters;

    /**
     * Create a request with a type.
     * @param type the type of the request for the action to handle
     */
    public Request(String type) {
        requestType = type;
        this.skills = new ArrayList<Skill>();
        this.filters = new ArrayList<Filter>();
    }

    public String getRequestType() {
        return requestType;
    }

    /**
     * Add a Skill. Create new One.
     * @param name the name of the Skill
     */
    public void addSkill(String name) {
        skills.add(new Skill(name));
    }

    /**
     * Add a Filter. Create new One.
     * @param name name of the Filter
     * @param value value of the Filter
     */
    public void addFilter(String name, int value) {
        filters.add(new Filter(name, value));
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }
}
