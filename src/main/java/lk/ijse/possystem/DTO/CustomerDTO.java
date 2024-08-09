package lk.ijse.possystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CustomerDTO implements Serializable {

    private String id;
    private String name;
    private String address;
    private double salary;

}
