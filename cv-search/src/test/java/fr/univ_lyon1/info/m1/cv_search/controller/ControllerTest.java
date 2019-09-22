package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.view.JfxView;
import fr.univ_lyon1.info.m1.cv_search.view.widget.FilterWidget;
import fr.univ_lyon1.info.m1.cv_search.view.widget.SearchWidget;
import fr.univ_lyon1.info.m1.cv_search.view.widget.SkillWidget;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        assertEquals(MjfxView.getResult(0), "John Smith");
        assertEquals(MjfxView.getResult(1), "Google");
        assertEquals(MjfxView.getResult(2), "2005");
        assertEquals(MjfxView.getResult(3), "2010");
        assertEquals(MjfxView.getResult(4), "c");
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
            skillWidget = new SkillWidget(controller);
            filterWidget = new FilterWidget(controller);
            searchWidget = new SearchWidget(controller,this);

            HBox searchSkillsBox = skillWidget.getSearchSkillsBox();
            root.getChildren().add(searchSkillsBox);

            Node strategicOptionsBox = filterWidget.getStrategicOptionsBox();
            root.getChildren().add(strategicOptionsBox);

            Node resultBox = searchWidget.getResultBox();
            root.getChildren().add(resultBox);

            // Everything's ready: add it to the scene and display it
            Scene scene = new Scene(root, 0, 0);
        }

        public String getResult(int i){
            return resultat.get(i);
        }

        public void addResults(List<String> answer, Map<String, List> answerApplicantsExperiences) {
            for (String name : answer) {
                List<Map> applicantExperiences = answerApplicantsExperiences.get(name);
                for (Map<String, Object> applicantExperience : applicantExperiences) {
                    String company = "";
                    String start = "";
                    String end = "";
                    List<String> keywords = new ArrayList<String>();

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
                    resultat.add(name);
                    resultat.add(company);
                    resultat.add(start);
                    resultat.add(end);
                    resultat.add(keywords.get(0));
                    int i = 0;
                }
            }

        }


    }
}

