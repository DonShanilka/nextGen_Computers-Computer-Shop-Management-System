package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.PlaceOrderDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {
    private static OrderModel orderModel = new OrderModel();
    private static ItemModel itemModel = new ItemModel();
    private static OrderDetailsModel orderDetailsModel = new OrderDetailsModel();

    public static boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException {

        String orderid = placeOrderDto.getOrderId();
        String customerId = placeOrderDto.getCustomerId();
        LocalDate date = placeOrderDto.getDate();

        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSave = orderModel.saveOrder(orderid,customerId,date);
            if (isOrderSave) {
                boolean isOrderDetailsSave = orderDetailsModel.saveOrderDetails(placeOrderDto.getOrderId(),placeOrderDto.getCartTmList());
                if (isOrderDetailsSave) {
                    boolean itemQtyUpdate = itemModel.updateItem(placeOrderDto.getCartTmList());
                    if (itemQtyUpdate){
                        connection.commit();
                    }
                }
                connection.rollback();
            }

        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }

}
