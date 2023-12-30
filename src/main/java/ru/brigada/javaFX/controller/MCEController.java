package ru.brigada.javaFX.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.brigada.javaFX.model.Element;
import ru.brigada.javaFX.model.ElementArrayList;
import ru.brigada.javaFX.model.ModalChangeElement;
import java.util.regex.Pattern;

public class MCEController {
    @FXML
    TextField changeTextField;
    @FXML
    Button changeButton;
    @FXML
    VBox vBox;
    public void changeElement(){
        String text = changeTextField.getText();
        if(!text.equals("")) {
            int value = Integer.parseInt(text);
            if (value > ElementArrayList.getMaxValue()) {
                ElementArrayList.setMaxValue(value);
            }
            Element element = ModalChangeElement.getElement();
            element.setNumber(value);
            element.setText(Integer.toString(value));

            MainController.reshape();
            Stage window = (Stage) vBox.getScene().getWindow();
            window.close();
        }
    }
    public void initialize(){
        Pattern pattern = Pattern.compile("(\\d*)?");
        changeTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!pattern.matcher(newValue).matches()||(newValue.length()>3)) changeTextField.setText(oldValue);
        }));
    }
}
