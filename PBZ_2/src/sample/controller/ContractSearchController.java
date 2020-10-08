package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    private ComboBox<Date> dateComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> organizations = Controller.getDatabase().getOrganizationNameFromContracts();
        organizationComboBox.setItems(organizations);
        searchButton.setOnAction(event -> {
            ObservableList<Contract> contracts = Controller.getDatabase().searchContracts(organizationComboBox.getValue(),
                                                                                          Date.valueOf(startDatePicker.getValue()));
            for (Contract contract : contracts) {
                System.out.println(contract.getId() + " " + contract.getStartDate() + " " + contract.getEndDate());
            }
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
