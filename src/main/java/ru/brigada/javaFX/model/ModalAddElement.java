package ru.brigada.javaFX.model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
