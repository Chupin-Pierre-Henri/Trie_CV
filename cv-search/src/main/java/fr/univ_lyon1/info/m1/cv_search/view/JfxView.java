package fr.univ_lyon1.info.m1.cv_search.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JfxView {
    private HBox searchSkillsBox;
    private VBox resultBox;
    private ComboBox<String> strategicOption;

    /**
     * Create the main view of the application.
     */
    public JfxView(Stage stage, int width, int height) {
        // Name of window
        stage.setTitle("Search for CV");

        VBox root = new VBox();

        Node newSkillBox = createNewSkillWidget();
        root.getChildren().add(newSkillBox);

        Node searchSkillsBox = createCurrentSearchSkillsWidget();
        root.getChildren().add(searchSkillsBox);
        
        List<String> strategies = new ArrayList<String>();
        strategies.add("50");
        strategies.add("60");
        strategicOption = createStrategicOptions(strategies);
        root.getChildren().add(strategicOption);

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
     * 
     * @param options
     * @return
     */
	private ComboBox<String> createStrategicOptions(List<String> options){
    	ObservableList<String> opts = FXCollections.observableList(options);
    	return new ComboBox<String>(opts);
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
                // TODO
				int value = 50;
            	if(strategicOption.getValue() == "50"){
            		value = 50;
            	}
            	

            	System.out.println(strategicOption.getValue());
            	if(strategicOption.getAccessibleText() == "60"){
            		value = 60;
            	}
				
                ApplicantList listApplicants
                    = new ApplicantListBuilder(new File(".")).build();
                resultBox.getChildren().clear();
                for (Applicant a : listApplicants) {
                	boolean selected = superiorToValue(a, value);
                    if (selected) {
                        resultBox.getChildren().add(new Label(a.getName()));
                    }
                }
            }

			private boolean superiorToValue(Applicant a, int value) {
				boolean selected = true;
				for (Node skill : searchSkillsBox.getChildren()) {
				    String skillName = ((Button) skill).getText();
				    if (a.getSkill(skillName) < value) {
				        selected = false;
				        break;
				    }
				}
				return selected;
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
}
