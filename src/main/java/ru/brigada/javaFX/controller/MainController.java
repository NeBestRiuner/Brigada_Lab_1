package ru.brigada.javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import ru.brigada.javaFX.model.Element;
import ru.brigada.javaFX.model.ElementArrayList;
import ru.brigada.javaFX.model.ModalAddElement;

public class MainController {
    static private Pane paneSort_S;
    @FXML
    Pane paneSort;


    public void addElementModal() throws Exception{
        ModalAddElement modalAddElement = new ModalAddElement();
        modalAddElement.newWindow();
    }
    public void initialize(){
        paneSort_S = paneSort;
        System.out.println(paneSort_S+" Сработало");
    }

    public static Pane getPaneSort_S() {
        return paneSort_S;
    }
}
