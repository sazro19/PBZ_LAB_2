package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Organization;
import sample.database.Staff;

import java.io.IOException;
import java.sql.SQLException;

public class AddOrganizationController {

    @FXML
    private TextField idTextField;

    @FXML
    private TextField fullnameTextField;

    @FXML
    private TextField shortnameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField bankNumberTextField;

    @FXML
    private TextField specialtyTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        addButton.setOnAction(event -> {
            try {
                Organization organization = new Organization(idTextField.getText(), fullnameTextField.getText(),
                                                             shortnameTextField.getText(), addressTextField.getText(),
                                                             bankNumberTextField.getText(),
                                                             specialtyTextField.getText());
                Controller.getDatabase().addOrganization(organization);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/organizationList.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Organizations");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cancelButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/organizationList.fxml"));
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setTitle("Organizations");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
