package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.SuppliyerDto;
import lk.ijse.model.SuppliyerModel;
import lk.ijse.tm.SuppliyerTm;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class SuppliyerController implements Initializable {

    @FXML
    private TableView<SuppliyerTm> SuppliyerTm;

    @FXML
    private TableColumn<?, ?> tmAddress;

    @FXML
    private TableColumn<?, ?> tmCompanyName;

    @FXML
    private TableColumn<?, ?> tmDate;

    @FXML
    private TableColumn<?, ?> tmEmail;

    @FXML
    private TableColumn<?, ?> tmMobile;

    @FXML
    private TableColumn<?, ?> tmName;

    @FXML
    private TableColumn<?, ?> tmNic;

    @FXML
    private TableColumn<?, ?> tmProductname;

    @FXML
    private TableColumn<?, ?> tmQty;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtQty;

    @FXML
    void supAddOnAction(ActionEvent event) {
        String nic = txtNic.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();
        String cName = txtCompanyName.getText();
        String pName = txtProductName.getText();
        String date = String.valueOf(txtDate.getValue());
        int qty = Integer.parseInt(txtQty.getText());

        var dto = new SuppliyerDto(nic, name, address, email, mobile, cName, pName, date, qty);

        try {
            boolean isSave = SuppliyerModel.saveSup(dto);

            if (isSave) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier is Save");
                clearfield();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier is Not Save");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void supDeleteOnAction(ActionEvent event) {
        String nic = txtNic.getText();

        var dto = new SuppliyerDto(nic);

        try {
            boolean isDelete = SuppliyerModel.deleteSup(dto);

            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier is Delete");
                clearfield();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier is Not Delete");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void supListOnAction(ActionEvent event) {

    }

    @FXML
    void supUpdateOnAction(ActionEvent event) {
        String nic = txtNic.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();
        String cName = txtCompanyName.getText();
        String pName = txtProductName.getText();
        String date = String.valueOf(txtDate.getValue());
        int qty = Integer.parseInt(txtQty.getText());

        var dto = new SuppliyerDto(nic, name, address, email, mobile, cName, pName, date, qty);

        try {
            boolean isSave = SuppliyerModel.updatesup(dto);

            if (isSave) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier is Save");
                clearfield();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier is Not Save");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAllSupplier(){
        var model = new SuppliyerModel();

        ObservableList<SuppliyerTm> obList = FXCollections.observableArrayList();

        try{
            List<SuppliyerDto> dtoList = model.getAll();

            for (SuppliyerDto dto : dtoList){
                obList.add(new SuppliyerTm(
                        dto.getNic(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getEmail(),
                        dto.getMobile(),
                        dto.getCName(),
                        dto.getPName(),
                        dto.getDate(),
                        dto.getQty()
                ));
            }
            SuppliyerTm.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void tableListener() {
        // Add listener to the table
        SuppliyerTm.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setSuppliyerData(newValue);
        });
    }

    public void setSuppliyerData(SuppliyerTm row) {
        if (row != null) {
            txtNic.setText(row.getNic());
            txtName.setText(row.getName());
            txtAddress.setText(row.getAddress());
            txtEmail.setText(row.getEmail());
            txtMobile.setText(row.getMobile());
            txtCompanyName.setText(row.getCName());
            txtProductName.setText(row.getPName());
            txtDate.setValue(LocalDate.parse(row.getDate())); // Assuming `DatePicker` handles LocalDate
            txtQty.setText(String.valueOf(row.getQty()));
        }
    }

    public void clearfield() {
        txtNic.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtMobile.setText("");
        txtCompanyName.setText("");
        txtProductName.setText("");
        txtDate.setValue(null); // Clear DatePicker
        txtQty.setText("");
    }

    private void setCellValueFactory() {
        tmNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tmMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tmCompanyName.setCellValueFactory(new PropertyValueFactory<>("cName"));
        tmProductname.setCellValueFactory(new PropertyValueFactory<>("pName"));
        tmDate.setCellValueFactory(new PropertyValueFactory<>("date")); // Assuming LocalDate
        tmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllSupplier();
        tableListener();
        clearfield();
    }
}
