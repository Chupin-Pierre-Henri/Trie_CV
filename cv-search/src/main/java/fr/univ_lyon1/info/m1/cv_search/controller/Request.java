package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.controller.component.Filter;
import fr.univ_lyon1.info.m1.cv_search.controller.component.Skill;

import java.util.ArrayList;
import java.util.List;

public class Request {
    protected List<Filter> filters;
    protected List<Skill> skills;

    public Request() {
        this.skills = new ArrayList<Skill>();
        this.filters = new ArrayList<Filter>();
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
