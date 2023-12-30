package ru.brigada.javaFX.model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class ModalChangeElement {
    static private Element element;
    public void newWindow(Element newElement){
        element = newElement;
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChangeWindow.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            window.setTitle("Модальное окно изменения элементов");
            window.setScene(scene);
            window.show();
        }catch(Exception exception){System.out.println(exception);}
    }
    public static Element getElement() {
        return element;
    }
    public static void setElement(Element element) {
        ModalChangeElement.element = element;
    }
}
