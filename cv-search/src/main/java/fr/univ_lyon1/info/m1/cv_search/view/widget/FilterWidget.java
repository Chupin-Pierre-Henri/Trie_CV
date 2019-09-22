package fr.univ_lyon1.info.m1.cv_search.view.widget;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.view.InputArea;
import fr.univ_lyon1.info.m1.cv_search.view.Style;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class FilterWidget {

    private VBox filterBar;
    private VBox strategicOptionsBox;
    private final List<String> listStrategy = new ArrayList<String>() {
        {
            add("average");
            add("superior");
            add("lower");
        }
    };

    public FilterWidget(Controller controller) {
        filterBar = new VBox();

        //create children
        Node strategicNode = createStrategicOptions(controller);
        Node strategicOptionsBox = createCurrentFiltersWidget();

        //add children to filterBar
        filterBar.getChildren().addAll(strategicNode,strategicOptionsBox);

        //style part
        Style.putStyle(filterBar);
    }

    /**
     * Create the Node for adding/deleting a Filter.
     *
     * @return Node for adding/deleting a Filter
     */
    protected Node createStrategicOptions(Controller controller) {
        HBox newFilterHeadBox = new HBox();

        //preparing child and add it
        Button addButton = new Button("Add Filter");
        Label labelFilter = new Label("Filters:");

        newFilterHeadBox.getChildren().addAll(labelFilter, addButton);
        newFilterHeadBox.setSpacing(10);

        //event part
        EventHandler<ActionEvent> filterHandlerAdd = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.addNewFilter();
            }
        };
        addButton.setOnAction(filterHandlerAdd);
        return newFilterHeadBox;
    }

    /**
     * Create the widget containing the different filter for search.
     *
     * @return Node
     */
    protected Node createCurrentFiltersWidget() {
        strategicOptionsBox = new VBox();
        return strategicOptionsBox;
    }

    public void removeFilter(int index) {
        strategicOptionsBox.getChildren().remove(index);
    }

    /**
     * create a new hbox in the view. Represent a filter.
     */
    public void createNewBox(Controller controller) {
        HBox newFilterBox = new HBox();
        newFilterBox.setId("filter");

        //create the children and add it
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

        //add filter to the bigger node
        strategicOptionsBox.getChildren().add(newFilterBox);

        //style part
        Style.putStyle(newFilterBox);

        //event part
        int indexOfComboBox = newFilterBox.getChildren().indexOf(dropdownMenu);
        int indexOfInputArea = newFilterBox.getChildren().indexOf(valueField);
        int index = strategicOptionsBox.getChildren().indexOf(newFilterBox);
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
                controller.removeFilter(index);
            }
        });
    }

    /**
     * Change the type of the comboBox.
     *
     * @param indexOfComboBox the index in the ComboBox
     * @param index           the index in the strategicOptionsBox
     * @param type            the filter name to change
     */
    public void changeTypeOnComboBox(int indexOfComboBox, int index, String type) {
        Node filterBox = strategicOptionsBox.getChildren().get(index);
        if (filterBox instanceof HBox) {
            Node comboBox = ((HBox) filterBox).getChildren().get(indexOfComboBox);
            if (comboBox instanceof ComboBox) {
                ((ComboBox<String>) comboBox).setValue(type);
            }
        }
    }

    /**
     * Change the type of the comboBox.
     *
     * @param indexOfComboBox the index in the ComboBox
     * @param index           the index in the strategicOptionsBox
     * @param text            the filter name to change
     */
    public void changeValueOnComboBox(int indexOfComboBox, int index, String text) {
        Node filterBox = strategicOptionsBox.getChildren().get(index);
        if (filterBox instanceof HBox) {
            Node inputArea = ((HBox) filterBox).getChildren().get(indexOfComboBox);
            if (inputArea instanceof InputArea) {
                ((InputArea) inputArea).setText(text);
            }
        }
    }

    public VBox getFilterBar() {
        return filterBar;
    }

    public VBox getStrategicOptionsBox() {
        return strategicOptionsBox;
    }
}
