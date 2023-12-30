package ru.brigada.javaFX.model;


import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class Element extends TextField {

    private int number = 0;
    public Element( int number){
        this.number = number;
        this.setText(Integer.toString(this.number));

        this.setAlignment(Pos.CENTER);
        this.setLayoutY(190);

        this.setOnMouseClicked(event -> {
            ModalChangeElement modalChangeElement = new ModalChangeElement();
            modalChangeElement.newWindow(this);
        });
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
