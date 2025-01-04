package lk.ijse.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SuppliyerTm {
    private String nic;
    private String name;
    private String address;
    private String email;
    private String mobile;
    private String cName;
    private String pName;
    private String date;
    private int qty;
}
