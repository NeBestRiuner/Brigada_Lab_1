package ru.brigada.javaFX;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
    public class App extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            System.out.println(getClass().getResource("/MainApp.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainApp.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setTitle("My First JavaFX App");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            Application.launch(args);
        }

    }

