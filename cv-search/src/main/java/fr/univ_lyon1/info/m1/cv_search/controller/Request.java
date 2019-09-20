package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.controller.component.Filter;
import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;

import java.util.ArrayList;
import java.util.List;

public class Request {
    protected String requestType;
    protected List<Skill> skills;
    protected List<Filter> filters;

    public Request(String type) {
        requestType = type;
        this.skills = new ArrayList<Skill>();
        this.filters = new ArrayList<Filter>();
    }

    public String getRequestType() {
        return requestType;
    }

    /**
	 * Add a Skill.
     * @param skill a Component to add in List's Request
     */
    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    /**
     * Add a Filter.
     * @param filter a Component to add in List's Request
     */
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }
}
