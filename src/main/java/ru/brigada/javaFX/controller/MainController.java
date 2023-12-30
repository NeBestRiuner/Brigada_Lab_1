package ru.brigada.javaFX.controller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import ru.brigada.javaFX.model.Element;
import ru.brigada.javaFX.model.ElementArrayList;
import ru.brigada.javaFX.model.ModalAddElement;
import javafx.scene.control.Button;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Pattern;

public class MainController {
    static private Pane paneSort_S;
    @FXML
    Pane paneSort;
    @FXML
    Button removeButton, sortButton, generateButton;
    @FXML
    TextField numTextField;
    static Timer timer;
    public void addElementModal() throws Exception{
            ModalAddElement modalAddElement = new ModalAddElement();
            modalAddElement.newWindow();
    }
    public void removeAllElements(){

            paneSort.getChildren().clear();
            ElementArrayList.getInstance().clear();
            ElementArrayList.setMaxValue(0);

    }
    public void sortAllElements(){
            if (!ElementArrayList.getInstance().isEmpty()) {
                reshape_sort(0,ElementArrayList.getInstance().size()/2);
                this.timer = new Timer(System.nanoTime());
                timer.start();
            }
    }
    public void generateButton(){

            int i = 0;
            while (i < Integer.parseInt(numTextField.getText())) {
                Random rnd = new Random();
                int ans = rnd.nextInt(9) + 1;
                Element element2 = new Element(ans);
                if (ans > ElementArrayList.getMaxValue()) {
                    ElementArrayList.setMaxValue(ans);
                }
                paneSort_S.getChildren().add(element2);
                ElementArrayList.getInstance().add(element2);
                reshape();
                i++;
            }

    }
    public static void reshape(){
        ListIterator<Element> iter = ElementArrayList.getInstance().listIterator();
        Pane paneSort = paneSort_S;
        double x = paneSort.getWidth() / ElementArrayList.getInstance().size();
        int i = 0;
        while (iter.hasNext()) {

            Element elementI = iter.next();
            System.out.println(elementI.getNumber());
            elementI.setLayoutX(x * i);
            elementI.setMinWidth(x);
            elementI.setMaxWidth(x);
            double height = (double) elementI.getNumber() / (double) ElementArrayList.getMaxValue() * 200;
            elementI.setMinHeight(height);
            elementI.setMaxHeight(height);
            elementI.setLayoutY(400 - height);
            i++;
        }
    }
    public static void reshape_sort(int i1, int i2){
        ListIterator<Element> iter = ElementArrayList.getInstance().listIterator();
        Pane paneSort = paneSort_S;
        double x = paneSort.getWidth() / ElementArrayList.getInstance().size()/2;
        int i = 0;
        while (iter.hasNext()) {
            Element elementI = iter.next();
            System.out.println(elementI.getNumber());
            if(i<i2) {
                elementI.setLayoutX(x * i + 300);
            }else{
                elementI.setLayoutX(x*(i-i2)+300);
            }
            elementI.setMinWidth(x);
            elementI.setMaxWidth(x);
            double height = (double) elementI.getNumber() / (double) ElementArrayList.getMaxValue() * 180;
            elementI.setMinHeight(height);
            elementI.setMaxHeight(height);
            if(i<i2) {
                elementI.setLayoutY(400 - height);
            }else{
                elementI.setLayoutY(200 - height);
            }
            i++;
        }
    }
    public static void reshape_merge(int i1, int i2, int i3){
        ListIterator<Element> iter = ElementArrayList.getInstance().listIterator();
        Pane paneSort = paneSort_S;
        double x = paneSort.getWidth() / ElementArrayList.getInstance().size()/2;
        int i = 0;
        while (iter.hasNext()) {
            Element elementI = iter.next();
            System.out.println(elementI.getNumber());
            if(i<i3){
                elementI.setLayoutX(x*i);
            }else{
                if(i<i2) {
                    elementI.setLayoutX(x * (i-i1) + 300);
                }else{
                    elementI.setLayoutX(x*(i-i2)+300);
                }
            }
            elementI.setMinWidth(x);
            elementI.setMaxWidth(x);
            double height = (double) elementI.getNumber() / (double) ElementArrayList.getMaxValue() * 180;
            elementI.setMinHeight(height);
            elementI.setMaxHeight(height);
            if (i<i3){
                elementI.setLayoutY(300-height);
            }else{
                if(i<i2) {
                    elementI.setLayoutY(400 - height);
                }else{
                    elementI.setLayoutY(200 - height);
                }
            }
            i++;
        }
    }
    public void initialize(){
        paneSort_S = paneSort;
        System.out.println(paneSort_S+" Сработало");
        Pattern pattern = Pattern.compile("(\\d*)?");
        numTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!pattern.matcher(newValue).matches()||(newValue.length()>3)) numTextField.setText(oldValue);
        }));

    }
    public static Pane getPaneSort_S() {
        return paneSort_S;
    }
    public static Timer getTimer(){
        return timer;
    }
}
