package lk.ijse.dto;

import lk.ijse.tm.CartTm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PlaceOrderDto {
    private String orderId;
    private LocalDate date;
    private String customerId;
    private int qty;
    private double totalPrice;
    private List<CartTm> cartTmList = new ArrayList<>();

    public PlaceOrderDto(String orderId, String date, String cusId, List<CartTm> cartTmList) {
        this.orderId = orderId;
        this.date = LocalDate.parse(date);
        this.customerId = cusId;
        this.qty = cartTmList.size();
        this.totalPrice = cartTmList.stream().mapToDouble(CartTm::getTotal_price).sum();
        this.cartTmList = cartTmList;
    }
}
