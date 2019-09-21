package fr.univ_lyon1.info.m1.cv_search;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;
import fr.univ_lyon1.info.m1.cv_search.view.JfxView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {

    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Controller controller = new Controller();

        // First view, provided in skeleton
        new JfxView(controller,stage, 600, 600);

        // Second view
        new JfxView(controller, new Stage(), 400, 400);
    }


    /**
     * A main method in case the user launches the application using
     * App as the main class.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
