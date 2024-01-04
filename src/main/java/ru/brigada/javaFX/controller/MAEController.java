package ru.brigada.javaFX.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.brigada.javaFX.model.Element;
import ru.brigada.javaFX.model.ElementArrayList;
import java.util.regex.Pattern;

public class MAEController {

    @FXML
    TextField addTextField;
    @FXML
    Button addButton;
    @FXML
    VBox vBox;

    public void addElement(){
        String text = addTextField.getText();
        if(!text.equals("")) {
            int value = Integer.parseInt(text);
            if (value > ElementArrayList.getMaxValue()) {
                ElementArrayList.setMaxValue(value);
            }
            Element element = new Element( value);
            Pane paneSort = MainController.getScrollPane_S();
            paneSort.getChildren().add(element);
            ElementArrayList.getInstance().add(element);
            MainController.reshape();
            Stage window = (Stage) vBox.getScene().getWindow();
            window.close();
        }
    }
    public void initialize(){
        Pattern pattern = Pattern.compile("(\\d*)?");
        addTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!pattern.matcher(newValue).matches()||(newValue.length()>3)) addTextField.setText(oldValue);
        }));
    }

}
