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
    protected HBox searchSkillsBox;
    protected VBox strategicOptionsBox;
    protected VBox resultBox;

    protected Controller controller;

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
    public JfxView(Controller controller, Stage stage, int width, int height) {
        this.controller = controller;
        this.controller.addView(this);
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

    protected JfxView() {
    }

    /**
     * Create the Node for choising/adding a strategy.
     */
    protected Node createStrategicOptions() {
        HBox newFilterHeadBox = new HBox();
        Button addButton = new Button("Add Filter");
        Label labelFilter = new Label("Filters:");

        newFilterHeadBox.getChildren().addAll(addButton, labelFilter);
        newFilterHeadBox.setSpacing(10);

        EventHandler<ActionEvent> filterHandlerAdd = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.addNewFilter();
            }
        };
        addButton.setOnAction(filterHandlerAdd);
        return newFilterHeadBox;
    }

    public void createNewBox() {
        HBox newFilterBox = new HBox();
        newFilterBox.setId("filter");

        //create the children
        ObservableList<String> opts = FXCollections.observableList(listStrategy);
        ComboBox<String> dropdownMenu = new ComboBox<String>(opts);
        Label labelValue = new Label("to value:");
        InputArea valueField = new InputArea();
        Button removeButton = new Button("X");

        dropdownMenu.setId("type");
        valueField.setId("value");

        //add children
        newFilterBox.getChildren().addAll(dropdownMenu,
                labelValue,
                valueField,
                removeButton
        );
        newFilterBox.setSpacing(10);

        //add to the bigger part
        strategicOptionsBox.getChildren().add(newFilterBox);
        int indexOfComboBox = newFilterBox.getChildren().indexOf(dropdownMenu);
        int indexOfInputArea = newFilterBox.getChildren().indexOf(valueField);
        int index = strategicOptionsBox.getChildren().indexOf(newFilterBox);

        //event part
        dropdownMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String value = dropdownMenu.getValue();
                controller.changeType(indexOfComboBox, index, value);
            }
        });
        valueField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String value = valueField.getText();
                controller.changeValue(indexOfInputArea, index, value);
            }
        });
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.removeBox(index);
            }
        });
    }

    /**
     * Create the text field to enter a new skill.
     */
    protected Node createNewSkillWidget() {
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

                controller.addNewSkill(text);

                textField.setText("");
                textField.requestFocus();
            }
        };
        submitButton.setOnAction(skillHandler);
        textField.setOnAction(skillHandler);
        return newSkillBox;
    }

    public void createNewSkill(String text) {
        Button skillBtn = new Button(text);
        skillBtn.setId("skill");
        searchSkillsBox.getChildren().add(skillBtn);
        int index = searchSkillsBox.getChildren().indexOf(skillBtn);
        skillBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.removeSkill(index);
            }
        });
    }

    /**
     * Create the widget showing the list of applicants.
     */
    protected Node createResultsWidget() {
        resultBox = new VBox();
        return resultBox;
    }

    /**
     * Create the widget used to trigger the search.
     */
    protected Node createSearchWidget() {
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resultBox.getChildren().clear();

                Request request = new Request("search");
                addSkillToRequest(request);
                addFilterToRequest(request);

                controller.handleRequest(request);
            }
        });
        return search;
    }

    /**
     * add all skill in searchSkillBox to the request.
     * @param request the target
     */
    protected void addSkillToRequest(Request request) {
        for (Node skill : searchSkillsBox.getChildren()) {
            // casting Node into Button for getting his value
            if (skill.getId().equals("skill") && skill instanceof Button) {
                String text = ((Button) skill).getText();
                request.addSkill(text);
            }
        }
    }

    /**
     * add all Filter in strategicOptionsBox to the request.
     * @param request the target
     */
    protected void addFilterToRequest(Request request) {
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
                                //cast node in InputArea for getting his text
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
    }

    /**
     * Create the widget showing the list of skills currently searched.
     */
    protected Node createCurrentSearchSkillsWidget() {
        searchSkillsBox = new HBox();
        return searchSkillsBox;
    }

    /**
     * Create the widget containing the different filter for search.
     */
    protected Node createCurrentFiltersWidget() {
        strategicOptionsBox = new VBox();
        return strategicOptionsBox;
    }

    public void removeFilter(int index) {
        strategicOptionsBox.getChildren().remove(index);
    }

    public void removeSkill(int index) {
        searchSkillsBox.getChildren().remove(index);
    }

    public void addResults(List<String> answer) {
        for (String name : answer) {
            resultBox.getChildren().add(new Label(name));
        }
    }

    public void changeTypeOnComboBox(int indexOfComboBox, int index, String type) {
        Node filterBox = strategicOptionsBox.getChildren().get(index);
        if (filterBox instanceof HBox) {
            Node comboBox = ((HBox) filterBox).getChildren().get(indexOfComboBox);
            if (comboBox instanceof ComboBox) {
                ((ComboBox<String>) comboBox).setValue(type);
            }
        }
    }

    public void changeValueOnComboBox(int indexOfComboBox, int index, String text) {
        Node filterBox = strategicOptionsBox.getChildren().get(index);
        if (filterBox instanceof HBox) {
            Node inputArea = ((HBox) filterBox).getChildren().get(indexOfComboBox);
            if (inputArea instanceof InputArea) {
                ((InputArea) inputArea).setText(text);
            }
        }
    }
}
