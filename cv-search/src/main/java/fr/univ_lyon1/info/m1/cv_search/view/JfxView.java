package fr.univ_lyon1.info.m1.cv_search.view;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.controller.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JfxView {
    private HBox searchSkillsBox;
    private VBox strategicOptionsBox;
    private VBox resultBox;

    private Controller controller;

    private final List<String> listStrategy = new ArrayList<String>() {
        {
            add("average");
            add("superior");
            add("lower");
        }
    };

    /**
     * Create the main view of the application.
     */
    public JfxView(Stage stage, int width, int height) {
        controller = new Controller(); // TODO here or in App?
        // Name of window
        stage.setTitle("Search for CV");

        VBox root = new VBox();

        Node newSkillBox = createNewSkillWidget();
        root.getChildren().add(newSkillBox);

        Node searchSkillsBox = createCurrentSearchSkillsWidget();
        root.getChildren().add(searchSkillsBox);

        Node strategicOptionsBox = createCurrentFiltersWidget();
        root.getChildren().add(strategicOptionsBox);

        Node strategicNode = createStrategicOptions();
        root.getChildren().add(strategicNode);

        Node search = createSearchWidget();
        root.getChildren().add(search);

        Node resultBox = createResultsWidget();
        root.getChildren().add(resultBox);

        // Everything's ready: add it to the scene and display it
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Create the Node for choising/adding a strategy.
     */
    private Node createStrategicOptions() {
        HBox newFilterHeadBox = new HBox();
        Button addButton = new Button("Add Filter");
        Label labelFilter = new Label("Filters:");

        newFilterHeadBox.getChildren().addAll(addButton, labelFilter);
        newFilterHeadBox.setSpacing(10);

        // One filter
        HBox newFilterBox = createNewBox();
        strategicOptionsBox.getChildren().add(newFilterBox);

        EventHandler<ActionEvent> filterHandlerAdd = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HBox newFilterBox = createNewBox();
                strategicOptionsBox.getChildren().add(newFilterBox);
            }
        };
        addButton.setOnAction(filterHandlerAdd);
        return newFilterHeadBox;
    }

    private HBox createNewBox() {
        HBox newFilterBox = new HBox();
        newFilterBox.setId("filter");

        ObservableList<String> opts = FXCollections.observableList(listStrategy);
        ComboBox<String> dropdownMenu = new ComboBox<String>(opts);
        Label labelValue = new Label("to value:");
        InputArea valueField = new InputArea();
        Button removeButton = new Button("X");

        dropdownMenu.setId("type");
        valueField.setId("value");

        newFilterBox.getChildren().addAll(dropdownMenu,
                labelValue,
                valueField,
                removeButton
        );
        newFilterBox.setSpacing(10);

        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                strategicOptionsBox.getChildren().remove(newFilterBox);
            }
        });
        return newFilterBox;
    }

    /**
     * Create the text field to enter a new skill.
     */
    private Node createNewSkillWidget() {
        HBox newSkillBox = new HBox();
        Label labelSkill = new Label("Skill:");
        TextField textField = new TextField();
        Button submitButton = new Button("Add skill");
        newSkillBox.getChildren().addAll(labelSkill, textField, submitButton);
        newSkillBox.setSpacing(10);

        EventHandler<ActionEvent> skillHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = textField.getText().trim();
                if (text.equals("")) {
                    return; // Do nothing
                }

                Button skillBtn = new Button(text);
                skillBtn.setId("skill");
                searchSkillsBox.getChildren().add(skillBtn);
                skillBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        searchSkillsBox.getChildren().remove(skillBtn);
                    }
                });

                textField.setText("");
                textField.requestFocus();
            }
        };
        submitButton.setOnAction(skillHandler);
        textField.setOnAction(skillHandler);
        return newSkillBox;
    }

    /**
     * Create the widget showing the list of applicants.
     */
    private Node createResultsWidget() {
        resultBox = new VBox();
        return resultBox;
    }

    /**
     * Create the widget used to trigger the search.
     */
    private Node createSearchWidget() {
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resultBox.getChildren().clear();

                // todo Refactor this method

                Request request = new Request("search");
                for (Node skill : searchSkillsBox.getChildren()) {
                    // casting Node into Button for getting is value
                    if (skill.getId().equals("skill") && skill instanceof Button) {
                        // clear
                        String text = ((Button) skill).getText();
                        request.addSkill(text);
                    }
                }

                for (Node strategy : strategicOptionsBox.getChildren()) {
                    List<Node> listNode;
                    if (strategy.getId().equals("filter") && strategy instanceof HBox) {
                        listNode = ((HBox) strategy).getChildren();
                    } else {
                        listNode = new ArrayList<Node>();
                    }

                    int value = 0;
                    String type = "";
                    for (Node node : listNode) {
                        if (node.getId() != null) {
                            switch (node.getId()) {
                                case "type":
                                    if (node instanceof ComboBox) {
                                        type = ((ComboBox<String>) node).getValue();
                                    }
                                    break;
                                case "value":
                                    if (node instanceof InputArea) {
                                        value = Integer.parseInt(((InputArea) node).getText());
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    request.addFilter(type, value);
                }

                List<String> answer = controller.handleRequest(request);
                for (String name : answer) {
                    resultBox.getChildren().add(new Label(name));
                }
            }
        });
        return search;
    }

    /**
     * Create the widget showing the list of skills currently searched
     * for.
     */
    private Node createCurrentSearchSkillsWidget() {
        searchSkillsBox = new HBox();
        return searchSkillsBox;
    }

    /**
     * Create the widget containing the different filter for search.
     */
    private Node createCurrentFiltersWidget() {
        strategicOptionsBox = new VBox();
        return strategicOptionsBox;
    }
}
