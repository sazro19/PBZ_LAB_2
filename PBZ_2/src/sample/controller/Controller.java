package sample.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Database;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {

    private static Database database;

    @FXML
    private Button organizationOperationButton;

    @FXML
    private Button agentOperationButton;

    @FXML
    private Button staffOperationButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        database = new Database();
        organizationOperationButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/organizationList.fxml"));
                Stage stage = (Stage) organizationOperationButton.getScene().getWindow();
                stage.setTitle("Organizations");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        agentOperationButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/agentList.fxml"));
                Stage stage = (Stage) agentOperationButton.getScene().getWindow();
                stage.setTitle("Agents");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        staffOperationButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/staffList.fxml"));
                Stage stage = (Stage) staffOperationButton.getScene().getWindow();
                stage.setTitle("Staff");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static Database getDatabase() {
        return database;
    }
}
