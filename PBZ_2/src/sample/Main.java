package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.Database;

import java.sql.*;
import java.util.Properties;

public class Main extends Application {
    private static final String url = "jdbc:mysql://localhost:3306/second_lab?useUnicode=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "sasha19062001";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private Properties p;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}
