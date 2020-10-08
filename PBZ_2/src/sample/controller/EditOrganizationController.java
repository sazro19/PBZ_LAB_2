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
import sample.database.Organization;

import java.io.IOException;
import java.sql.SQLException;

public class EditOrganizationController {

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
    private Button editButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        idTextField.setText(OrganizationListController.getSelectedOrganization().getId());
        fullnameTextField.setText(OrganizationListController.getSelectedOrganization().getFullName());
        shortnameTextField.setText(OrganizationListController.getSelectedOrganization().getShortName());
        addressTextField.setText(OrganizationListController.getSelectedOrganization().getAddress());
        bankNumberTextField.setText(OrganizationListController.getSelectedOrganization().getBankNumber());
        specialtyTextField.setText(OrganizationListController.getSelectedOrganization().getSpecialty());
        editButton.setOnAction(event -> {
            try {
                Organization newOrganization = new Organization(idTextField.getText(), fullnameTextField.getText(),
                                                                shortnameTextField.getText(), addressTextField.getText(),
                                                                bankNumberTextField.getText(),
                                                                specialtyTextField.getText());
                Controller.getDatabase().editOrganization(OrganizationListController.getSelectedOrganization(),
                                                          newOrganization);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/organizationList.fxml"));
                Stage stage = (Stage) editButton.getScene().getWindow();
                stage.setTitle("Organizations");
                stage.setScene(new Scene(root, 750, 550));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cancelButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/organizationList.fxml"));
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setTitle("Organizations");
                stage.setScene(new Scene(root, 750, 550));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
