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

        for (JfxView view : views){
            view.addResults(answer);
        }
    }

    public void addNewFilter() {
        for (JfxView view : views){
            view.createNewBox();
        }
    }

    public void addNewSkill(String text) {
        for (JfxView view : views){
            view.createNewSkill(text);
        }
    }

    public void removeBox(int index) {
        for (JfxView view : views){
            view.removeFilter(index);
        }
    }

    public void removeSkill(int index) {
        for (JfxView view : views){
            view.removeSkill(index);
        }
    }

    public void addView(JfxView view) {
        this.views.add(view);
    }

    public void changeType(int indexOfComboBox, int index, String value) {
        for (JfxView view : views) {
            view.changeTypeOnComboBox(indexOfComboBox, index, value);
        }
    }

    public void changeValue(int indexOfComboBox, int index, String value) {
        for (JfxView view : views) {
            view.changeValueOnComboBox(indexOfComboBox, index, value);
        }
    }
}
