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
import sample.database.Agent;

import java.io.IOException;
import java.sql.SQLException;

public class AgentListController {

    @FXML
    private TableView<Agent> agentTableView;

    @FXML
    private TableColumn<Agent, String> fullnameColumn;

    @FXML
    private TableColumn<Agent, String> passportDataColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button cancelButton;

    private static Agent selectedAgent;

    private static ObservableList<Agent> agents;

    private void createAgentTable() {
        agents = FXCollections.observableArrayList(Controller.getDatabase().getAgentList());
        agentTableView.setItems(agents);
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<Agent, String>("fullName"));
        passportDataColumn.setCellValueFactory(new PropertyValueFactory<Agent, String>("passportData"));
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        createAgentTable();
        addButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/addAgent.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Adding");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(event -> {
            try {
                selectedAgent = agentTableView.getSelectionModel().getSelectedItem();
                if (selectedAgent != null) {
                    Parent root = FXMLLoader.load(Main.class.getResource("view/editAgent.fxml"));
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
                selectedAgent = agentTableView.getSelectionModel().getSelectedItem();
                if (selectedAgent != null){
                Controller.getDatabase().deleteAgent(getSelectedAgent());
                agents = FXCollections.observableArrayList(Controller.getDatabase().getAgentList());
                agentTableView.setItems(agents);
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

    public static Agent getSelectedAgent(){
        return selectedAgent;
    }

    private void showError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Select your agent");
        alert.showAndWait();
    }
}