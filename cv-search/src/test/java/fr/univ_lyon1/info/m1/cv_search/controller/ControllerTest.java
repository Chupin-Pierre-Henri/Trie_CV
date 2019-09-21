package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.view.JfxView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControllerTest {

    protected Controller c;
    protected Request r;
    protected  MockJfxView MjfxView;

    @Before
    public void setUp() {
        c = new Controller();
        MjfxView = new MockJfxView(c);
        c.addView(MjfxView);
        r = new Request("search");
        r.addSkill("c");
        r.addFilter("superior",60);
    }

    @Test
    public void handleSearchRequest() {
        List<String> ret = new ArrayList<String>();
        c.handleRequest(r);
        String res = MjfxView.getResult(0);
        assertEquals(res, "John Smith");
    }


    public class MockJfxView extends JfxView {

        private List<String> resultat;

        /**
         * Create the main view of the application.
         *
         * @param controller
         */
        public MockJfxView(Controller controller) {
            this.controller = controller;
            this.resultat = new ArrayList<String>();
            // Name of window

            VBox root = new VBox();


            Node searchSkillsBox = createCurrentSearchSkillsWidget();
            root.getChildren().add(searchSkillsBox);

            Node strategicOptionsBox = createCurrentFiltersWidget();
            root.getChildren().add(strategicOptionsBox);

            Node resultBox = createResultsWidget();
            root.getChildren().add(resultBox);

            // Everything's ready: add it to the scene and display it
            Scene scene = new Scene(root, 0, 0);
        }

        public String  getResult(int i){
            return resultat.get(i);
        }

        public void addResults(List<String> answer) {
            for (String name : answer) {
                resultat.add(name);
            }
        }


    }
}

