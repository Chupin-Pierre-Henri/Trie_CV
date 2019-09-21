package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantListBuilder;
import fr.univ_lyon1.info.m1.cv_search.controller.strategy.Strategy;
import fr.univ_lyon1.info.m1.cv_search.controller.strategy.StrategyBuilder;
import fr.univ_lyon1.info.m1.cv_search.view.JfxView;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

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
     * @return the name of candidates meeting all the criteria requested
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
        Button skillBtn = new Button(text);
        for (JfxView view : views){
            view.createNewSkill(text);
        }
    }

    public void removeBox(HBox filterBox) {
        for (JfxView view : views){
            view.removeFilter(filterBox);
        }
    }

    public void removeSkill(Button skillBtn) {
        for (JfxView view : views){
            view.removeSkill(skillBtn);
        }
    }

    public void addView(JfxView view) {
        this.views.add(view);
    }
}
