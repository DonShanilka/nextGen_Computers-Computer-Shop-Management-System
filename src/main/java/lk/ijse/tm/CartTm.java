package lk.ijse.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartTm {

    private String item_id;
    private String item_name;
    private String item_price;
    private int item_qty;
    private double total_price;
    private double discount;
    private double new_price;

    public CartTm(String iCode, String des, double unitPrice, int qty, double discount, double total) {

        this.item_id = iCode;
        this.item_name = des;
        this.item_price = String.valueOf(unitPrice);
        this.item_qty = qty;
        this.total_price = total;
        this.discount = discount;
        this.new_price = (total - discount);
    }
}
