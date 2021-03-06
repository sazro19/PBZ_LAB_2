package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.database.Contract;
import sample.database.Staff;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class ContractSearchController {
    @FXML
    private Button searchButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> organizationComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TableView<Contract> resultTableView;

    @FXML
    private TableColumn<Contract, String> idColumn;

    @FXML
    private TableColumn<Contract, Date> startDateColumn;

    @FXML
    private TableColumn<Contract, Date> endDateColumn;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> organizations = Controller.getDatabase().getOrganizationNameFromContracts();
        organizationComboBox.setItems(organizations);

        searchButton.setOnAction(event -> {
            ObservableList<Contract> contracts = Controller.getDatabase().searchContracts(organizationComboBox.getValue(),
                                                                                          Date.valueOf(startDatePicker.getValue()));
            resultTableView.setItems(contracts);
            idColumn.setCellValueFactory(new PropertyValueFactory<Contract, String>("id"));
            startDateColumn.setCellValueFactory(new PropertyValueFactory<Contract, Date>("startDate"));
            endDateColumn.setCellValueFactory(new PropertyValueFactory<Contract, Date>("endDate"));
        });
        cancelButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/contractsList.fxml"));
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.setTitle("Contracts");
                stage.setScene(new Scene(root, 950, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
