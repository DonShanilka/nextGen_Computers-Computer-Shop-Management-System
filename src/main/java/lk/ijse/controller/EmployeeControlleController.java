package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.model.EmployeeModel;
import lk.ijse.tm.EmployeeTm;

import java.sql.SQLException;

public class EmployeeControlleController {
    public TextField txtNic;
    public TableColumn <?,?> tmNic;
    public TableView <EmployeeTm> employyeTm;


    public void empSaveOnAction(ActionEvent actionEvent) {

        String nic = txtNic.getText();

        var dto = new EmployeeDto(nic);

        try {
            boolean isSave = EmployeeModel.saveEmp(dto);

            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
