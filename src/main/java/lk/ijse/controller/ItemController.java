package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.ItemDto;
import lk.ijse.model.ItemModel;
import lk.ijse.tm.ItemTm;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    @FXML
    private TableView<ItemTm> Gputm;

    @FXML
    private TableColumn<?, ?> tmGpuBrand;

    @FXML
    private TableColumn<?, ?> tmGpuDate;

    @FXML
    private TableColumn<?, ?> tmGpuId;

    @FXML
    private TableColumn<?, ?> tmGpuModelNo;

    @FXML
    private TableColumn<?, ?> tmGpuPrice;

    @FXML
    private TableColumn<?, ?> tmGpuQty;

    @FXML
    private TableColumn<?, ?> tmGpuSpec;

    @FXML
    private TableColumn<?, ?> tmGpuSupId;

    @FXML
    private TableColumn<?, ?> tmGpuType;

    @FXML
    private TableColumn<?, ?> tmGpuYear;

    @FXML
    private AnchorPane txt;

    @FXML
    private TextField txtItemBrand;

    @FXML
    private DatePicker txtItemBuyDate;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtItemModel;

    @FXML
    private TextField txtItemPrice;

    @FXML
    private TextField txtItemQty;

    @FXML
    private TextArea txtItemSpec;

    @FXML
    private JFXComboBox<?> txtItemSuppliyerID;

    @FXML
    private JFXComboBox<?> txtItemType;

    @FXML
    private TextField txtItemYear;


    @FXML
    void itemDeleteOnAction(ActionEvent event) {
        String id = txtItemId.getText();

        var dto = new ItemDto(id);

        try {
            boolean isDelete = ItemModel.accDelete(dto);

            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION,"Item is Delete");
                clearFields();
                loadAllItem();
            } else {
                new Alert(Alert.AlertType.ERROR,"Item is Not Delete");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void itemGetAction(ActionEvent event) {

    }

    @FXML
    void itemUpdateOnAction(ActionEvent event) {
        String id = txtItemId.getText();
        String brand = txtItemBrand.getText();
        String model = txtItemModel.getText();
        String year = txtItemYear.getText();
        double price = Double.parseDouble(txtItemPrice.getText());
        String spec = txtItemSpec.getText();
//        String supId = String.valueOf(txtItemSuppliyerID.getValue());
        LocalDate date = txtItemBuyDate.getValue();
        int qty = Integer.parseInt(txtItemQty.getText());
//        String type = (String) txtItemType.getValue();

        var dto = new ItemDto(id,brand,model,year,price,spec,date,qty);

        try {
            boolean isUpdate = ItemModel.accUpdate(dto);

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item is Update");
                clearFields();
                loadAllItem();
            } else {
                new Alert(Alert.AlertType.ERROR,"Item is Not Update");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void itemaddOnAction(ActionEvent event) {
        String id = txtItemId.getText();
        String brand = txtItemBrand.getText();
        String model = txtItemModel.getText();
        String year = txtItemYear.getText();
        double price = Double.parseDouble(txtItemPrice.getText());
        String spec = txtItemSpec.getText();
        LocalDate date = txtItemBuyDate.getValue();
        int qty = Integer.parseInt(txtItemQty.getText());

        var dto = new ItemDto(id,brand,model,year,price,spec,date,qty);

        try {
            boolean isSave = ItemModel.saveItem(dto);

            if (isSave) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item is Save").show();
                clearFields();
                loadAllItem();
            } else {
                new Alert(Alert.AlertType.ERROR, "Item is Not Save");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAllItem(){
        var model = new ItemModel();

        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<ItemDto> dtoList = model.accGetAll();

            for (ItemDto dto : dtoList) {
                obList.add(new ItemTm(
                        dto.getId(),
                        dto.getBrand(),
                        dto.getModelno(),
                        dto.getYear(),
                        dto.getPrice(),
                        dto.getSpec(),
                        dto.getDate(),
                        dto.getQty()
                ));
            }
            Gputm.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearFields() {
        txtItemBrand.setText("");
        txtItemBuyDate.setValue(null); // Clears DatePicker
        txtItemId.setText("");
        txtItemModel.setText("");
        txtItemPrice.setText("");
        txtItemQty.setText("");
        txtItemSpec.setText(""); // Clears TextArea
//        txtItemType.setValue(null); // Clears ComboBox
        txtItemYear.setText("");
    }


    private void setCellValueFactory(){
        tmGpuId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tmGpuBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tmGpuModelNo.setCellValueFactory(new PropertyValueFactory<>("modelno"));
        tmGpuYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        tmGpuPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tmGpuSpec.setCellValueFactory(new PropertyValueFactory<>("spec"));
        tmGpuDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tmGpuQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllItem();
        setCellValueFactory();
        clearFields();
    }
}
