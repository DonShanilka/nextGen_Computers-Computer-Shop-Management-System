package lk.ijse.tm;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ItemTm {

    private String id;
    private String brand;
    private String modelno;
    private String year;
    private double price;
    private String spec;
    private LocalDate date;
    private int qty;

}
