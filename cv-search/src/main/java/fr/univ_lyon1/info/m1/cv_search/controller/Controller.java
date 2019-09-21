package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.controller.strategy.Strategy;
import fr.univ_lyon1.info.m1.cv_search.controller.strategy.StrategyBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantListBuilder;
import fr.univ_lyon1.info.m1.cv_search.view.JfxView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    protected List<JfxView> views;

    protected Request request;

    public Controller() {
        views = new ArrayList<JfxView>();
    }

    /**
     * receive a request and execute it.
     *
     * @param req the Request
     */
    public void handleRequest(Request req) {
        this.request = req;
        List<String> ret = new ArrayList<String>();

        if (req.getRequestType().equals("search")) {
            this.executeSearchStrategyRequest();
        }
    }

    /**
     * performs search on applicants based on received filtres (strategy) and applicants' skills.
     */
    private void executeSearchStrategyRequest() {
        StrategyBuilder stratBuild = new StrategyBuilder(request.getFilters(), request.getSkills());
        Strategy strategy = stratBuild.getStrategy();
        ApplicantList listApplicants
                = new ApplicantListBuilder(new File(".")).build();


        List<String> answer = new ArrayList<String>();
        answer = strategy.filter(listApplicants).getNamesOfApplicants();

        for (JfxView view : views) {
            view.addResults(answer);
        }
    }

    /**
     * the controller adds for all the view a filter.
     */
    public void addNewFilter() {
        for (JfxView view : views) {
            view.createNewBox();
        }
    }

    /**
     * the controller adds for all the view a skill.
     */
    public void addNewSkill(String text) {
        for (JfxView view : views) {
            view.createNewSkill(text);
        }
    }

    /**
     * the controller remove for all the view the filter.
     */
    public void removeFilter(int index) {
        for (JfxView view : views) {
            view.removeFilter(index);
        }
    }

    /**
     * the controller remove for all the view the Skill.
     */
    public void removeSkill(int index) {
        for (JfxView view : views) {
            view.removeSkill(index);
        }
    }

    /**filter
     * add new view.
     * @param view to add
     */
    public void addView(JfxView view) {
        this.views.add(view);
    }

    /**
     * the controller change for all the view the type.
     * @param indexOfComboBox the index in the ComboBox
     * @param index the index in the strategicOptionsBox
     * @param value the filter name to change
     */
    public void changeType(int indexOfComboBox, int index, String value) {
        for (JfxView view : views) {
            view.changeTypeOnComboBox(indexOfComboBox, index, value);
        }
    }

    /**
     * the controller change for all the view the type.
     * @param indexOfComboBox the index in the ComboBox
     * @param index the index in the strategicOptionsBox
     * @param value the Value name to change
     */
    public void changeValue(int indexOfComboBox, int index, String value) {
        for (JfxView view : views) {
            view.changeValueOnComboBox(indexOfComboBox, index, value);
        }
    }
}
