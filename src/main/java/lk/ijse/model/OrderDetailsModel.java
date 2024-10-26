package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsModel {
    public static boolean saveOrderDetails(String orderId, List<CartTm> cartTmList) throws SQLException {
        for (CartTm tm : cartTmList){
            if (!saveOrderDetails(orderId, tm)) {
                return false;
            }
        }
        return true;
    }

    private static boolean saveOrderDetails(String orderId, CartTm tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO order_details VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,orderId);
        pstm.setString(2, tm.getItem_id());
        pstm.setInt(3, tm.getItem_qty());
        pstm.setDouble(4, tm.getNew_price());

        return pstm.executeUpdate() > 0;
    }
}
