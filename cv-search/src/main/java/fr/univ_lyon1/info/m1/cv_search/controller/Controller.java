package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantListBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.Strategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.StrategyBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    protected Request request;

    public Controller() {
    }

    /**
     * receive a request and execute it.
     *
     * @param req the Request
     * @return the answer of Request
     */
    public List<String> handleRequest(Request req) {
        this.request = req;

        return this.executeRequest();
    }

    private List<String> executeRequest() {
        List<String> result = new ArrayList<String>();
        StrategyBuilder stratBuild = new StrategyBuilder(request.getFilters(), request.getSkills());
        Strategy strategy = stratBuild.getStrategy();
        ApplicantList listApplicants
                = new ApplicantListBuilder(new File(".")).build();
        result = strategy.filter(listApplicants).getNamesOfApplicants();
        return result;
    }
}
