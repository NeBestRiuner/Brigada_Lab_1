package ru.brigada.javaFX.controller;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import ru.brigada.javaFX.model.Element;
import ru.brigada.javaFX.model.ElementArrayList;
import ru.brigada.javaFX.model.ModalAddElement;
import javafx.scene.control.Button;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Pattern;

public class MainController {
    static private Pane scrollPane_S;
    @FXML
    Pane paneSort;

    Pane scrollPane;
    @FXML
    Button removeButton, sortButton, generateButton;
    ScrollPane sc;
    @FXML
    TextField numTextField;
    static Timer timer;
    public void addElementModal() throws Exception{
        if(timer!=null) {
            if (!timer.isRunning()) {
                ModalAddElement modalAddElement = new ModalAddElement();
                modalAddElement.newWindow();
            }
        }else{
            ModalAddElement modalAddElement = new ModalAddElement();
            modalAddElement.newWindow();
        }
    }
    public void removeAllElements(){
            if(timer!=null) {
                if(!timer.isRunning()){
                    scrollPane.getChildren().clear();
                    ElementArrayList.getInstance().clear();
                    ElementArrayList.setMaxValue(0);
                }
            }else{
                scrollPane.getChildren().clear();
                ElementArrayList.getInstance().clear();
                ElementArrayList.setMaxValue(0);
            }
    }
    public void sortAllElements(){
            if(timer!=null){
                if(!timer.isRunning()){
                    if (!ElementArrayList.getInstance().isEmpty()) {
                        this.timer = new Timer(System.nanoTime());
                        timer.start();
                    }
                }
            }else{
                this.timer = new Timer(System.nanoTime());
                timer.start();
            }
    }
    public void generateButton() {
        if (timer != null) {
            if (!timer.isRunning()) {
                int i = 0;
                while (i < Integer.parseInt(numTextField.getText())) {
                    Random rnd = new Random();
                    int ans = rnd.nextInt(9) + 1;
                    Element element2 = new Element(ans);
                    if (ans > ElementArrayList.getMaxValue()) {
                        ElementArrayList.setMaxValue(ans);
                    }
                    if(ElementArrayList.getInstance().size()>15){
                          scrollPane.setMinWidth(ElementArrayList.getInstance().size()*40);
                    }
                    scrollPane.getChildren().add(element2);
                    ElementArrayList.getInstance().add(element2);
                    reshape();
                    i++;
                }
            }
        } else {
            int i = 0;

            while (i < Integer.parseInt(numTextField.getText())) {
                Random rnd = new Random();
                int ans = rnd.nextInt(9) + 1;
                Element element2 = new Element(ans);
                if (ans > ElementArrayList.getMaxValue()) {
                    ElementArrayList.setMaxValue(ans);
                }
                if(ElementArrayList.getInstance().size()>15){
                    scrollPane.setMinWidth(ElementArrayList.getInstance().size()*40);
                }
                scrollPane.getChildren().add(element2);
                ElementArrayList.getInstance().add(element2);
                reshape();
                i++;
            }
        }
    }
    public static void reshape(){
        ListIterator<Element> iter = ElementArrayList.getInstance().listIterator();
        int i = 0;
        while (iter.hasNext()) {
            Element elementI = iter.next();
            System.out.println(elementI.getNumber());
            int width = 40;
            elementI.setLayoutX(i*width);
            elementI.setMinWidth(width);
            elementI.setMaxWidth(width);
            int height = 50;
            elementI.setMinHeight(height);
            elementI.setMaxHeight(height);
            elementI.setLayoutY(150);
            i++;
        }

    }
    public static void reshape_sort(int n, Element [][] twoDiam){
        int i;
        for(i = 0; i < ElementArrayList.getInstance().size(); i++){
                twoDiam[i/n][i%n].setLayoutY(((int)i/n)*100+50);
                System.out.println(twoDiam[i/n][i%n].getLayoutY()+"lY");
                twoDiam[i/n][i%n].setLayoutX((40*(i%n))+ElementArrayList.getInstance().size()*40);
        }
        if(ElementArrayList.getInstance().size()>7){
            scrollPane_S.setMinWidth(ElementArrayList.getInstance().size()*40+(40*n));
        }
        if(ElementArrayList.getInstance().size()>15){
            scrollPane_S.setMinHeight(100*n);
        }

    }
    public void initialize(){
        this.sc = new ScrollPane();
        sc.setPannable(true);
        paneSort.getChildren().add(sc);
        this.scrollPane = new Pane();
        scrollPane.setPrefSize(600,400);
        sc.setFitToHeight(true);
        sc.setFitToWidth(true);
        sc.setContent(this.scrollPane);
        scrollPane_S = scrollPane;
        Pattern pattern = Pattern.compile("(\\d*)?");
        numTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!pattern.matcher(newValue).matches()||(newValue.length()>3)) numTextField.setText(oldValue);
        }));

    }
    public static Pane getScrollPane_S() {
        return scrollPane_S;
    }
    public static Timer getTimer(){
        return timer;
    }
}
