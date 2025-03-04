package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ItemDto;
import lk.ijse.tm.CartTm;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {

    public static boolean saveItem(ItemDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO item VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getId());
        pstm.setString(2,dto.getBrand());
        pstm.setString(3,dto.getModelno());
        pstm.setString(4,dto.getYear());
        pstm.setDouble(5,dto.getPrice());
        pstm.setString(6,dto.getSpec());
        pstm.setDate(7, Date.valueOf(dto.getDate()));
        pstm.setInt(8,dto.getQty());

        return pstm.executeUpdate() > 0;
    }


    public static boolean accDelete(ItemDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM item WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1 ,dto.getId());
        return pstm.executeUpdate()>0;
    }


    public static boolean accUpdate(ItemDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE item SET brand=?, modelno=?, year=?, price=?, spec=?, date=?, qty=? WHERE id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getBrand());
        pstm.setString(2,dto.getModelno());
        pstm.setString(3,dto.getYear());
        pstm.setDouble(4,dto.getPrice());
        pstm.setString(5,dto.getSpec());
        pstm.setDate(6, Date.valueOf(dto.getDate()));
        pstm.setInt(7,dto.getQty());
        pstm.setString(8,dto.getId());

        return pstm.executeUpdate()>0;
    }


    public List<ItemDto> accGetAll() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM item";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<ItemDto> itemList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            itemList.add(new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getDate(7).toLocalDate(),
                    resultSet.getInt(8)

            ));
        }

        return itemList;
    }


    public static ItemDto searchItemId(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM item WHERE id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        ItemDto dto = null;

        if(resultSet.next()) {
            String brand = resultSet.getString(2);
            String modelno = resultSet.getString(3);
            String year = resultSet.getString(4);
            double price = resultSet.getDouble(5);
            String spec = resultSet.getString(6);
            LocalDate date = resultSet.getDate(7).toLocalDate();
            int qty = resultSet.getInt(8);

            dto = new ItemDto(id, brand, modelno, year, price, spec, date, qty);
        }
        return dto;
    }

    public static boolean updateItem(List<CartTm> cartTmList) throws SQLException {
        for (CartTm tm : cartTmList){
            if (!updateQty(tm.getItem_id(), tm.getItem_qty())){
                return false;
            }
        }
        return true;
    }

    public static boolean updateQty(String code, int qty) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE item SET qty = qty - ? WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1,qty);
        pstm.setString(2,code);

        return pstm.executeUpdate() > 0;
    }

}
