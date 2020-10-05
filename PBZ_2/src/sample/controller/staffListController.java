package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.Agent;
import sample.database.Database;
import sample.database.Staff;

import java.sql.SQLException;

public class staffListController {
    private Database database;

    @FXML
    private TableView<Staff> staffTableView;

    @FXML
    private TableColumn<Staff, String> fullnameColumn;

    @FXML
    private TableColumn<Staff, Integer> ageColumn;

    @FXML
    private TableColumn<Staff, String> riskCategoryColumn;

    private void createStaffTable() {
        System.out.println(database.getAgentList().size());
        ObservableList<Staff> test = FXCollections.observableArrayList(database.getStaffList());
        staffTableView.setItems(test);
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("fullName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("age"));
        riskCategoryColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("riskCategory"));
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        database = new Database();
        createStaffTable();
    }
}