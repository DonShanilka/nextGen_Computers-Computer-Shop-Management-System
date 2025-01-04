package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class DashBordController {
    @FXML
    private AnchorPane root1;

    public JFXButton customer;
    public JFXButton employee;
    public JFXButton home;
    public JFXButton item;
    public JFXButton supplier;
    public JFXButton order;

    public void initialize() throws IOException {
        //homeOnAction(null);
    }

    void setForm(String form) throws IOException {
        String[] formArray = {"/view/customer.fxml", "/view/employee.fxml", "/view/income.fxml", "/view/item.fxml", "/view/suppliyer.fxml", "/view/placeOrder.fxml"};

        JFXButton[] btnArray = {customer,employee,home,item, supplier,order};
        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(form)));
        root1.getChildren().clear();
        root1.getChildren().add(load);
        for (int i = 0; i < formArray.length; i++) {
            btnArray[i].setStyle("-fx-background-color:  #ffffff; -fx-font-size: 22; -fx-text-fill: #000f69");

            if (form.equals(formArray[i])){
                btnArray[i].setStyle("-fx-background-color: #000f69; -fx-font-size: 22; -fx-text-fill: #ffffff");
            }
        }
    }

    @FXML
    void customerOnAction(ActionEvent event) throws IOException {
        setForm("/view/customer.fxml");
    }

    @FXML
    void employeeOnAction(ActionEvent event) throws IOException {
        setForm("/view/employee.fxml");
    }

    @FXML
    void homeOnAction(ActionEvent event) throws IOException {
        setForm("/view/income.fxml");
    }

    @FXML
    void itemOnAction(ActionEvent event) throws IOException {
        setForm("/view/item.fxml");
    }

    @FXML
    void suppliyerOnAction(ActionEvent actionEvent) throws IOException {
        setForm("/view/suppliyer.fxml");
    }

    @FXML
    void placeOderOnAction(ActionEvent actionEvent) throws IOException {
        setForm("/view/placeOrder.fxml");
    }
}
