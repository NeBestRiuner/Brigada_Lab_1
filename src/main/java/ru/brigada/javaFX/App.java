package ru.brigada.javaFX;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.brigada.javaFX.controller.MainController;

public class App extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            System.out.println(getClass().getResource("/MainApp.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainApp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("My First JavaFX App");
            primaryStage.setScene(scene);
            primaryStage.show();
            EventHandler<WindowEvent> windowEventEventHandler = WindowEvent ->{
                if(MainController.getTimer().isRunning()){
                    MainController.getTimer().stop();
                }
            };
            primaryStage.setOnCloseRequest(windowEventEventHandler);
        }

        public static void main(String[] args) {
            Application.launch(args);
        }

    }

