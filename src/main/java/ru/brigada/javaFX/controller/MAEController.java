package ru.brigada.javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.brigada.javaFX.model.Element;
import ru.brigada.javaFX.model.ElementArrayList;

import java.util.ListIterator;
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
        int value = Integer.parseInt(text);
        if(value>ElementArrayList.getMaxValue()){
            ElementArrayList.setMaxValue(value);
        }
        Element element = new Element(0,value);
        Pane paneSort = MainController.getPaneSort_S();
        paneSort.getChildren().add(element);
        ElementArrayList.getInstance().add(element);
        ListIterator<Element> iter = ElementArrayList.getInstance().listIterator();
        double x = paneSort.getWidth()/ElementArrayList.getInstance().size();
        int i = 0;
        while(iter.hasNext()){
            Element elementI = iter.next();
            elementI.setLayoutX(x*i);
            elementI.setMinWidth(x);
            elementI.setMaxWidth(x);
            double height = (double)elementI.getNumber()/(double)ElementArrayList.getMaxValue()*200;
            elementI.setMinHeight(height);
            elementI.setMaxHeight(height);
            elementI.setLayoutY(200-height);
            System.out.println(x*i);
            System.out.println(height+"//"+elementI.getNumber()+"//"+ElementArrayList.getMaxValue());
            System.out.println(200-height);
            i++;
        }
        Stage window = (Stage) vBox.getScene().getWindow();
        window.close();
        //Stage.getWindows().stream().filter(Window::isFocused);
    }
    public void initialize(){
        Pattern pattern = Pattern.compile("(\\d*)?");
        addTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!pattern.matcher(newValue).matches()||(newValue.length()>3)) addTextField.setText(oldValue);
        }));
    }

}
