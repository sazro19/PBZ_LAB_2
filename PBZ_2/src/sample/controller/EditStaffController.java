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

public class EditStaffController {

    @FXML
    private TextField fullnameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField categoryTextField;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        fullnameTextField.setText(StaffListController.getSelectedStaff().getFullName());
        ageTextField.setText(StaffListController.getSelectedStaff().getAge().toString());
        categoryTextField.setText(StaffListController.getSelectedStaff().getRiskCategory());
        editButton.setOnAction(event -> {
            try {
                Staff newStaff = new Staff(fullnameTextField.getText(), Integer.valueOf(ageTextField.getText()),
                                           categoryTextField.getText());
                Controller.getDatabase().editStaff(StaffListController.getSelectedStaff(), newStaff);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/staffList.fxml"));
                Stage stage = (Stage) editButton.getScene().getWindow();
                stage.setTitle("Staff");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        cancelButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/staffList.fxml"));
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setTitle("Staff");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
