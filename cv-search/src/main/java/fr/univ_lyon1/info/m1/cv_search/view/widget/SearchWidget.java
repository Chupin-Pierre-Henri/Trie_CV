package fr.univ_lyon1.info.m1.cv_search.view.widget;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.view.JfxView;
import fr.univ_lyon1.info.m1.cv_search.view.Style;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchWidget {
    private HBox searchBar;

    private VBox resultBox;
    private JfxView view;

    public SearchWidget(Controller controller, JfxView view){
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

    public HBox getSearchBar() {
        return searchBar;
    }

    public VBox getResultBox() {
        return resultBox;
    }
}
