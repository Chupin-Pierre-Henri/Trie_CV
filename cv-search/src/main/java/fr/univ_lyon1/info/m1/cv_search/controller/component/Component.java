package fr.univ_lyon1.info.m1.cv_search.controller.component;

public interface Component {
    /**
	 * if is Filter.
     * @return true if is Filter false else
     */
    boolean isFilter();

    /**
	 * if is Skill.
     * @return true if is Skill false else
     */
    boolean isSkill();

    String getName();
}
