package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Database;
import sample.database.Staff;

import java.io.IOException;
import java.sql.SQLException;

public class StaffListController {

    @FXML
    private TableView<Staff> staffTableView;

    @FXML
    private TableColumn<Staff, String> fullnameColumn;

    @FXML
    private TableColumn<Staff, Integer> ageColumn;

    @FXML
    private TableColumn<Staff, String> riskCategoryColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    private static Staff selectedStaff;

    private static ObservableList<Staff> staffList;

    private void createStaffTable() {
        staffList = FXCollections.observableArrayList(Controller.getDatabase().getStaffList());
        staffTableView.setItems(staffList);
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("fullName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("age"));
        riskCategoryColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("riskCategory"));
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        createStaffTable();
        addButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/addStaff.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Adding");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(event -> {
            try {
                selectedStaff = staffTableView.getSelectionModel().getSelectedItem();
                if (selectedStaff != null) {
                    Parent root = FXMLLoader.load(Main.class.getResource("view/editStaff.fxml"));
                    Stage stage = (Stage) editButton.getScene().getWindow();
                    stage.setTitle("Edit");
                    stage.setScene(new Scene(root, 650, 350));
                } else {
                    showError();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteButton.setOnAction(event -> {
            try {
                selectedStaff = staffTableView.getSelectionModel().getSelectedItem();
                if (selectedStaff != null){
                    Controller.getDatabase().deleteStaff(getSelectedStaff());
                    staffList = FXCollections.observableArrayList(Controller.getDatabase().getStaffList());
                    staffTableView.setItems(staffList);
                } else {
                    showError();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        cancelButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/sample.fxml"));
                Stage stage1 = (Stage) cancelButton.getScene().getWindow();
                stage1.setTitle("Учет договоров страхования");
                stage1.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Staff getSelectedStaff(){
        return selectedStaff;
    }

    private void showError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Select your staff");
        alert.showAndWait();
    }
}