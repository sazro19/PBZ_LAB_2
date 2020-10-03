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

import java.sql.SQLException;

public class ListController {
    private Database database;

    @FXML
    private TableView<Agent> agentTableView;

    @FXML
    private TableColumn<Agent, String> fullnameColumn;

    @FXML
    private TableColumn<Agent, String> passportDataColumn;

    private void hideTables() {
        agentTableView.setVisible(false);
    }

    private void createAgentTable() {
        hideTables();
        agentTableView.setVisible(true);
        System.out.println(database.getAgentList().size());
        ObservableList<Agent> test = FXCollections.observableArrayList(database.getAgentList());
        agentTableView.setItems(test);
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<Agent, String>("fullName"));
        passportDataColumn.setCellValueFactory(new PropertyValueFactory<Agent, String>("passportData"));
    }

    private void setInterface() {
        switch (Controller.getPointerInterface()) {
            case 3: {
                createAgentTable();
                break;
            }
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        hideTables();
        database = new Database();
        setInterface();
    }
}