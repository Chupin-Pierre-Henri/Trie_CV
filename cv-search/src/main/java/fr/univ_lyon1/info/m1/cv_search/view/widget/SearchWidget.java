package fr.univ_lyon1.info.m1.cv_search.view.widget;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.view.JfxView;
import fr.univ_lyon1.info.m1.cv_search.view.Style;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchWidget {
    private HBox searchBar;

    private VBox resultBox;
    protected JfxView view;

    protected SearchWidget() {
    }

    /**
     * construct the widget search for searching and sorting applicant,
     * display in an resultBox.
     * @param controller the controller to tell, in case of event
     * @param view the view to send the request
     */
    public SearchWidget(Controller controller, JfxView view) {
        this.view = view;
        createSearchWidget(controller);
        createResultsWidget();
    }

    /**
     * Create the widget used to trigger the search.
     *
     * @param controller controller for the event
     */
    protected void createSearchWidget(Controller controller) {
        searchBar = new HBox();

        //create children
        Button search = new Button("Search");
        CheckBox sort = new CheckBox("Sort");

        //add children
        searchBar.getChildren().addAll(search, sort);

        //style part
        Style.putStyle(searchBar);
        sort.setStyle("-fx-padding: 2;");

        //event part
        int index = searchBar.getChildren().indexOf(sort);
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resultBox.getChildren().clear();

                view.sendSearchRequest(sort.isSelected());
            }
        });
        sort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.checkSort(index, sort.isSelected());
            }
        });
    }

    /**
     * Create the widget showing the list of applicants.
     *
     * @return Node
     */
    protected Node createResultsWidget() {
        resultBox = new VBox();
        return resultBox;
    }

    /**
     * Change the value of the sort checkbox.
     *
     * @param index    the index in the searchBar
     * @param selected the filter name to change
     */
    public void changeSortValue(int index, boolean selected) {
        Node sortButton = searchBar.getChildren().get(index);
        if (sortButton instanceof CheckBox) {
            ((CheckBox) sortButton).setSelected(selected);
        }
    }

    /**
     * add the candidates who pass the filters.
     *
     * @param answer                      list of names that are to be added to the resultBox
     * @param answerApplicantsExperiences map of applicant and his experiences
     */
    public void addResults(List<String> answer, Map<String, List> answerApplicantsExperiences) {
        for (String name : answer) {
            addResult(answerApplicantsExperiences, name);
        }
    }

    private void addResult(Map<String, List> answerApplicantsExperiences, String name) {
        HBox applicant = new HBox();
        applicant.getChildren().add(new Label(name));

        //create the box of all experiences
        VBox experiencesBox = new VBox();

        List<Map> applicantExperiences = answerApplicantsExperiences.get(name);
        for (Map<String, Object> applicantExperience : applicantExperiences) {
            //create the box of one experience

            String company = "";
            String start = "";
            String end = "";
            List<String> keywords = new ArrayList<String>();
            //children of an experience box

            for (String type : applicantExperience.keySet()) {
                switch (type) {
                    case "company":
                        company = (String) applicantExperience.get(type);
                        break;
                    case "start":
                        start = (String) applicantExperience.get(type);
                        break;
                    case "end":
                        end = (String) applicantExperience.get(type);
                        break;
                    case "keywords":
                        keywords = (List<String>) applicantExperience.get(type);
                        break;
                    default:
                        break;
                }
            }

            VBox experienceBox = new VBox();

            HBox header = constructHeaderBox(company, start, end);
            Style.putStyle(header);
            experienceBox.getChildren().add(header);

            if (!keywords.isEmpty()) {
                HBox keywordsBody = constructBodyBox(keywords);
                Style.putStyle(keywordsBody);
                experienceBox.getChildren().add(keywordsBody);
            }
            Style.putStyle(experienceBox);
            experiencesBox.getChildren().add(experienceBox);
        }

        applicant.getChildren().add(experiencesBox);
        Style.putStyle(applicant);
        resultBox.getChildren().add(applicant);
    }

    private HBox constructBodyBox(List<String> keywords) {
        HBox keywordsBody = new HBox();
        keywordsBody.getChildren().add(new Label(" Keywords : "));

        VBox keywordBox = new VBox();
        for (String keyword : keywords) {
            keywordBox.getChildren().add(new Label(" * " + keyword));
        }
        keywordsBody.getChildren().add(keywordBox);
        return keywordsBody;
    }

    private HBox constructHeaderBox(String company, String start, String end) {
        HBox header = new HBox();
        //add header
        header.getChildren().add(new Label(" Company : "));
        header.getChildren().add(new Label(company));
        header.getChildren().add(new Label("    Start : "));
        header.getChildren().add(new Label(start));
        if (!end.equals("")) {
            header.getChildren().add(new Label("    End : "));
            header.getChildren().add(new Label(end));
        }
        return header;
    }

    public HBox getSearchBar() {
        return searchBar;
    }

    public VBox getResultBox() {
        return resultBox;
    }
}
