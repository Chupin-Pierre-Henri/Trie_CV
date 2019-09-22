package fr.univ_lyon1.info.m1.cv_search.view;

import javafx.scene.control.TextField;

public class InputArea extends TextField {
    private int value;

    public InputArea() {
        super();
    }

    private void onInitialization() {
        setText(Long.toString(this.value));
    }

    @Override
    public void replaceText(int startIndex, int endIndex, String text) {
        if (text.matches("[0-9]*")) {
            super.replaceText(startIndex, endIndex, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (text.matches("[0-9]*")) {
            super.replaceSelection(text);
        }
    }
}
