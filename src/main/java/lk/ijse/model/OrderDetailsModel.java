package lk.ijse.model;

import lk.ijse.tm.CartTm;

import java.util.List;

public class OrderDetailsModel {
    public static boolean saveOrderDetails(String orderId, List<CartTm> cartTmList){
        for (CartTm tm : cartTmList){
            if (!saveOrderDetails(orderId, tm)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save
}
