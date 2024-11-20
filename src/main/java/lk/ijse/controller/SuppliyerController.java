package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.SuppliyerDto;
import lk.ijse.model.EmployeeModel;
import lk.ijse.model.SuppliyerModel;
import lk.ijse.tm.EmployeeTm;

import java.sql.SQLException;
import java.util.List;

public class SuppliyerController {

    @FXML
    private TableView<String> SuppliyerTm;

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
    private TextField txtDate;

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
        String date = txtDate.getText();
        int qty = Integer.parseInt(txtQty.getText());

        var dto = new SuppliyerDto(nic,name,address,email,mobile,cName,pName,date,qty);

        try {
            boolean isSave = SuppliyerModel.saveSup(dto);

            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier is Save");
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

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier is Delete");
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
        String date = txtDate.getText();
        int qty = Integer.parseInt(txtQty.getText());

        var dto = new SuppliyerDto(nic,name,address,email,mobile,cName,pName,date,qty);

        try {
            boolean isSave = SuppliyerModel.updatesup(dto);

            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier is Save");
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier is Not Save");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

//public void loadAllEmployee(){
//    var model = new EmployeeModel();
//
//    ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
//
//    try{
//        List<EmployeeDto> dtoList = model.getAll();
//
//        for (EmployeeDto dto : dtoList){
//            obList.add(new EmployeeTm(
//                    dto.getNic(),
//                    dto.getName(),
//                    dto.getAddress(),
//                    dto.getEmail(),
//                    dto.getMobile(),
//                    dto.getBDate(),
//                    dto.getGender(),
//                    dto.getNation(),
//                    dto.getRelation(),
//                    dto.getEId(),
//                    dto.getRolle(),
//                    dto.getJDate(),
//                    dto.getDepart(),
//                    dto.getPQ(),
//                    dto.getExperiance(),
//                    dto.getUni()
//            ));
//        }
//        SuppliyerTm.setItems(obList);
//    } catch (SQLException e) {
//        throw new RuntimeException(e);
//    }
//}
