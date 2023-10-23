package ru.brigada.javaFX.model;


import javafx.geometry.Pos;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class Element extends TextField {
    private static  int sizeX = 0;
    private int number = 0;
    public Element(int x, int number){
        this.number = number;
        this.setText(Integer.toString(this.number));
        sizeX = x;
        this.setAlignment(Pos.CENTER);
        this.setLayoutY(190);
        Pattern pattern = Pattern.compile("(\\d*)?");
        this.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!pattern.matcher(newValue).matches()||(newValue.length()>3)) this.setText(oldValue);
        }));
    }
    public static void setSizeX(int x){
        sizeX = x;
    }

    public int getNumber() {
        return number;
    }
}
