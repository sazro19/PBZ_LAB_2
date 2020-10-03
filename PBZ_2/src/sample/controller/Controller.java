package sample.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class Controller {

    private static int pointerInterface;

    public static int getPointerInterface() {
        return pointerInterface;
    }

    @FXML
    private Button organizationOperationButton;

    @FXML
    private Button agentOperationButton;

    @FXML
    private Button staffOperationButton;

    @FXML
    void initialize() {
        organizationOperationButton.setOnAction(event -> {
            try {
                pointerInterface = 1;
                Parent root = FXMLLoader.load(Main.class.getResource("view/list.fxml"));
                Stage stage = (Stage) organizationOperationButton.getScene().getWindow();
                stage.setTitle("Organizationss");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        agentOperationButton.setOnAction(event -> {
            try {
                pointerInterface = 3;
                Parent root = FXMLLoader.load(Main.class.getResource("view/list.fxml"));
                Stage stage = (Stage) agentOperationButton.getScene().getWindow();
                stage.setTitle("Agents");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        staffOperationButton.setOnAction(event -> {
            try {
                pointerInterface = 2;
                Parent root = FXMLLoader.load(Main.class.getResource("view/list.fxml"));
                Stage stage = (Stage) staffOperationButton.getScene().getWindow();
                stage.setTitle("Staff");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
