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

public class SumSearchController {

    @FXML
    private Button searchButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TableView<Contract> resultTableView;

    @FXML
    private TableColumn<Contract, Date> startDateColumn;

    @FXML
    private TableColumn<Contract, String> sumColumn;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> category = FXCollections.observableArrayList("Первая", "Вторая", "Высшая");
        categoryComboBox.setItems(category);

        searchButton.setOnAction(event -> {
            ObservableList<Contract> contracts = Controller.getDatabase().searchSum(categoryComboBox.getValue(),
                    Date.valueOf(startDatePicker.getValue()));
            resultTableView.setItems(contracts);
            startDateColumn.setCellValueFactory(new PropertyValueFactory<Contract, Date>("startDate"));
            sumColumn.setCellValueFactory(new PropertyValueFactory<Contract, String>("sumCategory"));
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
