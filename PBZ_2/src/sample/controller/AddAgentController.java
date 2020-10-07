package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Agent;
import sample.database.Staff;

import java.io.IOException;
import java.sql.SQLException;

public class AddAgentController {

    @FXML
    private TextField fullnameTextField;

    @FXML
    private TextField passportDataTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        addButton.setOnAction(event -> {
            try {
                Agent agent = new Agent(fullnameTextField.getText(), passportDataTextField.getText());
                Controller.getDatabase().addAgent(agent);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/agentList.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Agents");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cancelButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/agentList.fxml"));
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setTitle("Agents");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

