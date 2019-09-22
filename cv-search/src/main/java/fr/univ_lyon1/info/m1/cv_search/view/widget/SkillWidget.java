package fr.univ_lyon1.info.m1.cv_search.view.widget;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.view.Style;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SkillWidget {

    private VBox skillBar;

    private HBox searchSkillsBox;

    /**
     * construct the widget skill for adding and deleting skill.
     * @param controller the controller to tell, in case of event
     */
    public SkillWidget(Controller controller) {
        skillBar = new VBox();

        //create children
        Node newSkillBox = createNewSkillWidget(controller);
        Node searchSkillsBox = createCurrentSearchSkillsWidget();

        //add children to skillbar
        skillBar.getChildren().addAll(newSkillBox, searchSkillsBox);

        //style part
        Style.putStyle(skillBar);
    }


    /**
     * Create the text field to enter a new skill.
     *
     * @return Node with the text field to enter a new skill
     */
    protected Node createNewSkillWidget(Controller controller) {
        HBox newSkillBox = new HBox();

        //preparing children node and add it
        Label labelSkill = new Label("Skill:");
        TextField textField = new TextField();
        Button submitButton = new Button("Add skill");

        newSkillBox.getChildren().addAll(labelSkill, textField, submitButton);
        newSkillBox.setSpacing(10);

        // event part
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

    /**
     * Create the widget showing the list of skills currently searched.
     *
     * @return Node
     */
    protected Node createCurrentSearchSkillsWidget() {
        searchSkillsBox = new HBox();
        return searchSkillsBox;
    }

    /**
     * create a new skill with name.
     *
     * @param text the name of the skill to add
     */
    public void createNewSkill(String text, Controller controller) {
        HBox box = new HBox();

        //create children
        Label label = new Label(" " + text + " ");
        Button skillBtn = new Button("x");
        box.setId("skill");
        box.setAccessibleText(text);

        //add children
        box.getChildren().addAll(label, skillBtn);

        //add to searchSkillsBox node
        searchSkillsBox.getChildren().add(box);

        //style part
        Style.putStyle(box);

        // event part
        int index = searchSkillsBox.getChildren().indexOf(box);
        skillBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.removeSkill(index);
            }
        });
    }

    public void removeSkill(int index) {
        searchSkillsBox.getChildren().remove(index);
    }

    public VBox getSkillBar() {
        return skillBar;
    }

    public HBox getSearchSkillsBox() {
        return searchSkillsBox;
    }
}
