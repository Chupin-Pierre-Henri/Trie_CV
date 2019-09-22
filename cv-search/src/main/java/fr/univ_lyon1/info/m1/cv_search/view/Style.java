package fr.univ_lyon1.info.m1.cv_search.view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Style {

    private static final String BASIC_STYLE = "-fx-padding: 2;" + "-fx-border-style: solid inside;"
            + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
            + "-fx-border-radius: 5;" + "-fx-border-color: black;";

    public static void putStyle(HBox box) {
        box.setStyle(BASIC_STYLE);
        box.setAlignment(Pos.CENTER_LEFT);
    }

    public static void putStyle(VBox box) {
        box.setStyle(BASIC_STYLE);
        box.setAlignment(Pos.CENTER_LEFT);
    }

}
