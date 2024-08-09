package lk.ijse.possystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemDTO implements Serializable {

    private String code;
    private String name;
    private double qty;
    private double price;

}
