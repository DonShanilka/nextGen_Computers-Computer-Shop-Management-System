package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.ItemDto;
import lk.ijse.model.CustomerModel;
import lk.ijse.model.ItemModel;
import lk.ijse.model.OrderModel;
import lk.ijse.tm.CartTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderController {

    @FXML
    private JFXComboBox<String> lblCustomerId;

    @FXML
    private JFXComboBox<String> lblItemId;

    @FXML
    private Label lbl_Qty_On_hand;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lbl_customer_name;

    @FXML
    private Label lbl_item_name;

    @FXML
    private Label lbl_item_price;

    @FXML
    private Label lbl_total_price;

    @FXML
    private TextField txt_qty;

    @FXML
    private TableView<CartTm> tm_Cart;

    @FXML
    private TableColumn<?, ?> tm_ItemName;

    @FXML
    private TableColumn<?, ?> tm_ItemPrice;

    @FXML
    private TableColumn<?, ?> tm_TotalPrice;

    @FXML
    private TableColumn<?, ?> tm_itemId;

    @FXML
    private TableColumn<?, ?> tm_itemQty;

    @FXML
    private TableColumn<?, ?> tm_Discount;

    @FXML
    private TextField txt_discount;

    OrderModel orderModel = new OrderModel();
    CustomerModel customerModel = new CustomerModel();
    ItemModel itemModel = new ItemModel();

    private ObservableList<CartTm> obList = FXCollections.observableArrayList();

    @FXML
    void addToCartOnAction(ActionEvent event) {
        String iCode = lblItemId.getValue();
        String des = lbl_item_name.getText();
        double unitPrice = Double.parseDouble(lbl_item_price.getText());
        int qty = Integer.parseInt(txt_qty.getText());
        double discount = qty * Double.parseDouble(txt_discount.getText());
        double total = (unitPrice * qty) - (discount * qty);

        try {
            if (!obList.isEmpty()) {
                for (int i = 0; i < tm_Cart.getItems().size(); i++) {
                    if (tm_itemId.getCellData(i).equals(iCode)) {
                        int col_Qty = (int) tm_itemQty.getCellData(i);
                        qty += col_Qty;
                        total = unitPrice - discount * qty;
                        obList.get(i).setItem_qty(String.valueOf(qty));
                        obList.get(i).setTotal_price(String.valueOf(total));

                        calulateTotal();
                        tm_Cart.refresh();
                        return;
                    }
                }
            }
            obList.add(new CartTm(iCode, des, unitPrice, qty, discount, total));
            tm_Cart.setItems(obList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void calulateTotal(){
        double total = 0.0;
        for (int i = 0; i < tm_Cart.getItems().size(); i++) {
            total += (double) tm_TotalPrice.getCellData(i);
        }
        lbl_total_price.setText("Rs : " + total);
    }

    @FXML
    void clearOnAction(ActionEvent event) {

    }


    private void genarateNextId(){
        String orderId;

        try {
            String lastId = orderModel.getLastId();
            System.out.println("Last ID : " + lastId);

            if (lastId == null) {
                orderId = "O-0001";
            } else {
                String[] ar = lastId.split("[O]");
                int digits = Integer.parseInt(ar[1]);
                digits++;
                String newId = String.format("%04d", digits);
                orderId =  newId;
            }
            System.out.println("Order ID : " + orderId);
            lblOrderId.setText(orderId);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setData(){
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    public void loadItemId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<ItemDto> idList = itemModel.accGetAll();

            for (ItemDto dto : idList) {
                obList.add(dto.getId());
            }
            lblItemId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {
        String id = lblItemId.getValue();

        try {
            ItemDto dto = itemModel.searchItemId(id);
            lbl_item_name.setText(dto.getSpec());
            lbl_item_price.setText(String.valueOf(dto.getPrice()));
            lbl_Qty_On_hand.setText(String.valueOf(dto.getQty()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void lodadCustomerId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try{
            List<CustomerDto> idList = customerModel.getAll();

            for (CustomerDto dto : idList) {
                obList.add(dto.getNic());
            }
            lblCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbCustomerOnAction(ActionEvent actionEvent) {
        String id = lblCustomerId.getValue();

        try {
            CustomerDto dto = customerModel.searchCustomer(id);
            lbl_customer_name.setText(dto.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void initialize(){
        lodadCustomerId();
        loadItemId();
        genarateNextId();
        setData();
        setCellValueFactory();
    }

    private void setCellValueFactory(){
        tm_itemId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        tm_ItemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        tm_ItemPrice.setCellValueFactory(new PropertyValueFactory<>("item_price"));
        tm_itemQty.setCellValueFactory(new PropertyValueFactory<>("item_qty"));
        tm_TotalPrice.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        tm_Discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
    }

}
