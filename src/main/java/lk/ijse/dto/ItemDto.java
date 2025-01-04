package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemDto {

    private String id;
    private String brand;
    private String modelno;
    private String year;
    private double price;
    private String spec;
    private String supid;
    private LocalDate date;
    private int qty;
    private String type;

    public ItemDto(String id) {
        this.id = id;
    }

    public ItemDto(String id, String brand, String model, String year, double price, String spec, LocalDate date, int qty) {
        this.id = id;
        this.brand = brand;
        this.modelno = model;
        this.year = year;
        this.price = price;
        this.spec = spec;
        this.date = date;
    }

//    public ItemDto(String id, String brand, String modelno, String year, double price, String spec, String supid, LocalDate date, int qty, String type) {
//        this.id = id;
//        this.brand = brand;
//        this.modelno = modelno;
//        this.year = year;
//        this.price = price;
//        this.spec = spec;
//        this.supid = supid;
//        this.date = date;
//        this.qty = qty;
//        this.type = type;
//    }
}
