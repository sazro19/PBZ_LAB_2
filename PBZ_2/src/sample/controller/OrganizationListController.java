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
import sample.database.Organization;

import java.io.IOException;
import java.sql.SQLException;

public class OrganizationListController {

    @FXML
    private TableView<Organization> organizationTableView;

    @FXML
    private TableColumn<Organization, String> idColumn;

    @FXML
    private TableColumn<Organization, String> fullnameColumn;

    @FXML
    private TableColumn<Organization, String> shortnameColumn;

    @FXML
    private TableColumn<Organization, String> addressColumn;

    @FXML
    private TableColumn<Organization, String> bankColumn;

    @FXML
    private TableColumn<Organization, String> specialtyColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    private static Organization selectedOrganization;

    private static ObservableList<Organization> organizations;

    private void createOrganizationTable() {
        organizations = FXCollections.observableArrayList(Controller.getDatabase().getOrganizationList());
        organizationTableView.setItems(organizations);
        idColumn.setCellValueFactory(new PropertyValueFactory<Organization, String>("id"));
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<Organization, String>("fullName"));
        shortnameColumn.setCellValueFactory(new PropertyValueFactory<Organization, String>("shortName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Organization, String>("address"));
        bankColumn.setCellValueFactory(new PropertyValueFactory<Organization, String>("bankNumber"));
        specialtyColumn.setCellValueFactory(new PropertyValueFactory<Organization, String>("specialty"));
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        createOrganizationTable();
        addButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/addOrganization.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Adding");
                stage.setScene(new Scene(root, 650, 550));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(event -> {
            try {
                selectedOrganization = organizationTableView.getSelectionModel().getSelectedItem();
                if (selectedOrganization != null) {
                    Parent root = FXMLLoader.load(Main.class.getResource("view/editOrganization.fxml"));
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
                selectedOrganization = organizationTableView.getSelectionModel().getSelectedItem();
                if (selectedOrganization != null){
                    Controller.getDatabase().deleteOrganization(getSelectedOrganization());
                    organizations = FXCollections.observableArrayList(Controller.getDatabase().getOrganizationList());
                    organizationTableView.setItems(organizations);
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

    private void showError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Select your organization");
        alert.showAndWait();
    }

    public static Organization getSelectedOrganization() {
        return selectedOrganization;
    }
}