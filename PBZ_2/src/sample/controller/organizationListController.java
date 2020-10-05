package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.Database;
import sample.database.Organization;

import java.sql.SQLException;

public class organizationListController {
    private Database database;

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

    private void createOrganizationTable() {
        System.out.println(database.getOrganizationList().size());
        ObservableList<Organization> test = FXCollections.observableArrayList(database.getOrganizationList());
        organizationTableView.setItems(test);
        idColumn.setCellValueFactory(new PropertyValueFactory<Organization, String>("id"));
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<Organization, String>("fullName"));
        shortnameColumn.setCellValueFactory(new PropertyValueFactory<Organization, String>("shortName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Organization, String>("address"));
        bankColumn.setCellValueFactory(new PropertyValueFactory<Organization, String>("bankNumber"));
        specialtyColumn.setCellValueFactory(new PropertyValueFactory<Organization, String>("specialty"));
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        database = new Database();
        createOrganizationTable();
    }
}