package ru.brigada.javaFX.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.brigada.javaFX.controller.MAEController;
import ru.brigada.javaFX.controller.MainController;

public class ModalAddElement {
    public void newWindow() throws Exception{
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddWindow.fxml"));
        Scene scene = new Scene(loader.load());

        window.setTitle("Модальное окно добавления элементов");
        window.setScene(scene);
        window.show();
    }
}
