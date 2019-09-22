package fr.univ_lyon1.info.m1.cv_search.view;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.controller.Request;
import fr.univ_lyon1.info.m1.cv_search.view.widget.FilterWidget;
import fr.univ_lyon1.info.m1.cv_search.view.widget.SearchWidget;
import fr.univ_lyon1.info.m1.cv_search.view.widget.SkillWidget;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JfxView {

    protected SearchWidget searchWidget;
    protected SkillWidget skillWidget;
    protected FilterWidget filterWidget;
    protected Controller controller;

    protected JfxView() {
    }

    /**
     * Create the main view of the application.
     */
    public JfxView(Controller controller, Stage stage, int width, int height) {
        this.controller = controller;
        this.controller.addView(this);
        // Name of window
        stage.setTitle("Search for CV");

        VBox root = new VBox();

        skillWidget = new SkillWidget(controller);
        Node skillBar = skillWidget.getSkillBar();
        root.getChildren().add(skillBar);

        filterWidget = new FilterWidget(controller);
        Node filterBar = filterWidget.getFilterBar();
        root.getChildren().add(filterBar);

        searchWidget = new SearchWidget(controller,this);
        Node searchBar = searchWidget.getSearchBar();
        root.getChildren().add(searchBar);

        Node resultBox = searchWidget.getResultBox();
        root.getChildren().add(resultBox);

        // Everything's ready: add it to the scene and display it
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * add all skill in searchSkillBox to the request.
     *
     * @param request the target
     */
    protected void addSkillToRequest(Request request) {
        HBox searchSkillsBox = skillWidget.getSearchSkillsBox();
        for (Node skill : searchSkillsBox.getChildren()) {
            // casting Node into Button for getting his value
            if (skill.getId().equals("skill")) {
                String text = skill.getAccessibleText();
                request.addSkill(text);
            }
        }
    }

    /**
     * add all Filter in strategicOptionsBox to the request.
     *
     * @param request the target
     */
    protected void addFilterToRequest(Request request) {
        VBox strategicOptionsBox = filterWidget.getStrategicOptionsBox();
        for (Node strategy : strategicOptionsBox.getChildren()) {
            //get Node of Strategy HBox
            List<Node> listNode;
            if (strategy.getId().equals("filter") && strategy instanceof HBox) {
                listNode = ((HBox) strategy).getChildren();
            } else {
                listNode = new ArrayList<Node>();
            }

            int value = 0;
            String type = "";
            for (Node node : listNode) {
                //search for the good node for value and type of Filter
                if (node.getId() != null) {
                    switch (node.getId()) {
                        case "type":
                            if (node instanceof ComboBox) {
                                //cast node in ComboBox for getting his value
                                type = ((ComboBox<String>) node).getValue();
                            }
                            break;
                        case "value":
                            if (node instanceof InputArea) {
                                String text = ((InputArea) node).getText();
                                if ( text != null && !text.equals("")) {
                                    //cast node in InputArea for getting his text
                                    value = Integer.parseInt(text);
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            if( type != null && !type.equals("")){
                request.addFilter(type, value);
            }
        }
    }

    public void sendSearchRequest(boolean selected) {
        Request request = new Request("search");
        addSkillToRequest(request);
        addFilterToRequest(request);
        if(selected) {
            request.addParameter("sort");
        }

        controller.handleRequest(request);
    }

    public void createNewSkill(String text) {
        skillWidget.createNewSkill(text,controller);
    }

    public void removeSkill(int index) {
        skillWidget.removeSkill(index);
    }

    public void createNewBox() {
        filterWidget.createNewBox(controller);
    }

    public void removeFilter(int index) {
        filterWidget.removeFilter(index);
    }

    public void changeTypeOnComboBox(int indexOfComboBox, int index, String type) {
        filterWidget.changeTypeOnComboBox(indexOfComboBox,index,type);
    }

    public void changeValueOnComboBox(int indexOfComboBox, int index, String value) {
        filterWidget.changeValueOnComboBox(indexOfComboBox,index,value);
    }

    public void changeSortValue(int index, boolean selected) {
        searchWidget.changeSortValue(index,selected);
    }

    public void addResults(List<String> answer, Map<String, List> answerExperience) {
        searchWidget.addResults(answer,answerExperience);
    }
}
