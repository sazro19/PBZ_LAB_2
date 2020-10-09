package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Contract;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ContractsListController {
    @FXML
    private TableView<Contract> contractTableView;

    @FXML
    private TableColumn<Contract, String> idColumn;

    @FXML
    private TableColumn<Contract, String> organizationIdColumn;

    @FXML
    private TableColumn<Contract, String> agentFullnameColumn;

    @FXML
    private TableColumn<Contract, Date> startDateColumn;

    @FXML
    private TableColumn<Contract, Date> endDateColumn;

    @FXML
    private TableColumn<Contract, String> sumCategoryColumn;

    @FXML
    private TableColumn<Contract, String> sumCaseColumn;

    @FXML
    private TableColumn<Contract, String> staffFullnameColumn;

    @FXML
    private Button contractSearchButton;

    @FXML
    private Button agentSearchButton;

    @FXML
    private Button sumSearchButton;

    @FXML
    private Button cancelButton;

    private static ObservableList<Contract> contractList;

    private void createContractTable() {
        contractList = FXCollections.observableArrayList(Controller.getDatabase().getContractList());
        contractTableView.setItems(contractList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Contract, String>("id"));
        organizationIdColumn.setCellValueFactory(new PropertyValueFactory<Contract, String>("organizationId"));
        agentFullnameColumn.setCellValueFactory(new PropertyValueFactory<Contract, String>("agentName"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<Contract, Date>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Contract, Date>("endDate"));
        sumCategoryColumn.setCellValueFactory(new PropertyValueFactory<Contract, String>("sumCategory"));
        sumCaseColumn.setCellValueFactory(new  PropertyValueFactory<Contract, String>("sumCase"));
        staffFullnameColumn.setCellValueFactory(new  PropertyValueFactory<Contract, String>("staffName"));

    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        createContractTable();
        contractSearchButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/contractSearch.fxml"));
                Stage stage = (Stage) contractSearchButton.getScene().getWindow();
                stage.setTitle("Search");
                stage.setScene(new Scene(root, 650, 550));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        sumSearchButton.setOnAction(event -> {
            try {
                    Parent root = FXMLLoader.load(Main.class.getResource("view/sumSearch.fxml"));
                    Stage stage = (Stage) sumSearchButton.getScene().getWindow();
                    stage.setTitle("Search");
                    stage.setScene(new Scene(root, 650, 650));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        agentSearchButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/agentSearch.fxml"));
                Stage stage = (Stage) sumSearchButton.getScene().getWindow();
                stage.setTitle("Search");
                stage.setScene(new Scene(root, 650, 650));
            } catch (IOException e) {
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

}
