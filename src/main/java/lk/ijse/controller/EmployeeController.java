package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.model.EmployeeModel;
import lk.ijse.tm.EmployeeTm;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    @FXML
    public TextField txtNation;
    @FXML
    public TableColumn<?,?> tmNation;
    @FXML
    private TableView <EmployeeTm> employyeTm;
    @FXML
    private TableColumn<?, ?> tmAddress;
    @FXML
    private TableColumn<?, ?> tmBday;
    @FXML
    private TableColumn<?, ?> tmDate;
    @FXML
    private TableColumn<?, ?> tmDepartment;
    @FXML
    private TableColumn<?, ?> tmEid;
    @FXML
    private TableColumn<?, ?> tmEmail;
    @FXML
    private TableColumn<?, ?> tmExperiance;
    @FXML
    private TableColumn<?, ?> tmGender;
    @FXML
    private TableColumn<?, ?> tmMobile;
    @FXML
    private TableColumn<?, ?> tmName;
    @FXML
    private TableColumn<?, ?> tmNic;
    @FXML
    private TableColumn<?, ?> tmQualification;
    @FXML
    private TableColumn<?, ?> tmRelation;
    @FXML
    private TableColumn<?, ?> tmRolle;
    @FXML
    private TableColumn<?, ?> tmUniname;
    @FXML
    private TextField txtAddress;
    @FXML
    private DatePicker txtBdaY;
    @FXML
    private ComboBox<String > txtDepartment;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtExpe;
    @FXML
    private ComboBox<String> txtGebder;
    @FXML
    private DatePicker txtJoinDate;
    @FXML
    private TextField txtMobile;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtNic;
    @FXML
    private TextField txtPQ;
    @FXML
    private TextField txteId;
    @FXML
    private TextField txtRolle;
    @FXML
    private ComboBox<String> txtRelation;
    @FXML
    private TextField txtUni;

    private String[] gen = {"Male", "FeMale"};
    private String[] rel = {"Marriage", "UnMarriage"};
    private String[] dep = {"HR", "Finance & Accounting", "Service", "IT"};

    EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    void empSaveOnAction(ActionEvent event) {
        String nic = txtNic.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();
        String bDate = String.valueOf(txtBdaY.getValue());
        String gender = txtGebder.getValue();
        String nation = txtNation.getText();
        String relation = txtRelation.getValue();
        String eId = txteId.getText();
        String rolle = txtRolle.getText();
        String jDate = String.valueOf(txtJoinDate.getValue());
        String depart = txtDepartment.getValue();
        String pQ = txtPQ.getText();
        String experiance = txtExpe.getText();
        String uni = txtUni.getText();

        var dto = new EmployeeDto(nic,name,address,email,mobile,bDate,gender,nation,relation,eId,rolle,jDate,depart,pQ,experiance,uni);

        try {
            boolean isSave = EmployeeModel.saveEmp(dto);

            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Save").show();
                loadAllEmployee();
                clearfield();
            } else {
                new Alert(Alert.AlertType.ERROR,"Employee Not Save Something is Worrying");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void empUpdateOnAction(ActionEvent event) {
        String nic = txtNic.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();
        String bDate = String.valueOf(txtBdaY.getValue());
        String gender = txtGebder.getValue();
        String nation = txtNation.getText();
        String relation = txtRelation.getValue();
        String eId = txteId.getText();
        String rolle = txtRolle.getText();
        String jDate = String.valueOf(txtJoinDate.getValue());
        String depart = txtDepartment.getValue();
        String pQ = txtPQ.getText();
        String experiance = txtExpe.getText();
        String uni = txtUni.getText();

        var dto = new EmployeeDto(nic,name,address,email,mobile,bDate,gender,nation,relation,eId,rolle,jDate,depart,pQ,experiance,uni);

        try{
            boolean isUpdate = EmployeeModel.updateEmployee(dto);

            if(isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION);
                loadAllEmployee();
                clearfield();
            } else {
                new Alert(Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void empDeleteOnAction(ActionEvent event) throws SQLException {
        String nic = txtNic.getText();

        var dto = new EmployeeDto(nic);

        try {
            boolean isDelete = EmployeeModel.deleteEmployee(dto);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted");
                loadAllEmployee();
                clearfield();
            } else {
                new Alert(Alert.AlertType.ERROR, "Employee Not Delete");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void empGetAllAction(ActionEvent event) {

    }

    @FXML
    void empIdQrOnAction(ActionEvent event) {

    }


    private void setCellValueFactory(){
        tmNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tmMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tmBday.setCellValueFactory(new PropertyValueFactory<>("bDate"));
        tmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tmNation.setCellValueFactory(new PropertyValueFactory<>("nation"));
        tmRelation.setCellValueFactory(new PropertyValueFactory<>("relation"));
        tmEid.setCellValueFactory(new PropertyValueFactory<>("eId"));
        tmRolle.setCellValueFactory(new PropertyValueFactory<>("rolle"));
        tmDate.setCellValueFactory(new PropertyValueFactory<>("jDate"));
        tmDepartment.setCellValueFactory(new PropertyValueFactory<>("depart"));
        tmQualification.setCellValueFactory(new PropertyValueFactory<>("pQ"));
        tmExperiance.setCellValueFactory(new PropertyValueFactory<>("experiance"));
        tmUniname.setCellValueFactory(new PropertyValueFactory<>("uni"));

    }

    public void loadAllEmployee(){
        var model = new EmployeeModel();

        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try{
            List<EmployeeDto> dtoList = model.getAll();

            for (EmployeeDto dto : dtoList){
                obList.add(new EmployeeTm(
                        dto.getNic(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getEmail(),
                        dto.getMobile(),
                        dto.getBDate(),
                        dto.getGender(),
                        dto.getNation(),
                        dto.getRelation(),
                        dto.getEId(),
                        dto.getRolle(),
                        dto.getJDate(),
                        dto.getDepart(),
                        dto.getPQ(),
                        dto.getExperiance(),
                        dto.getUni()
                ));
            }
            employyeTm.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void tableListener() {
        employyeTm.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
//            System.out.println(newValue);
            setData(newValue);
        });
    }

    public void setData(EmployeeTm row){
        txtNic.setText(row.getNic());
        txtName.setText(row.getName());
        txtAddress.setText(row.getAddress());
        txtEmail.setText(row.getEmail());
        txtMobile.setText(row.getMobile());
        txtBdaY.setUserData(row.getBDate());
        txtGebder.setValue(row.getGender());
        txtNation.setText(row.getNation());
        txtRelation.setValue(row.getRelation());
        txteId.setText(row.getEId());
        txtRolle.setText(row.getRolle());
        txtJoinDate.setUserData(row.getJDate());
        txtDepartment.setValue(row.getDepart());
        txtPQ.setText(row.getPQ());
        txtExpe.setText(row.getExperiance());
        txtUni.setText(row.getUni());
    }

    public void clearfield(){
        txtNic.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtMobile.setText("");
        txtBdaY.setUserData("");
        txtGebder.setValue("");
        txtNation.setText("");
        txtRelation.setValue("");
        txteId.setText("");
        txtRolle.setText("");
        txtJoinDate.setUserData("");
        txtDepartment.setValue("");
        txtPQ.setText("");
        txtExpe.setText("");
        txtUni.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearfield();
        tableListener();
        txtGebder.getItems().addAll(gen);
        txtRelation.getItems().addAll(rel);
        txtDepartment.getItems().addAll(dep);
        loadAllEmployee();
        setCellValueFactory();

    }
}

