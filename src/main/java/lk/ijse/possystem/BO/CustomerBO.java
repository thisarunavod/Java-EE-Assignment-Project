package lk.ijse.possystem.BO;

import lk.ijse.possystem.DTO.CustomerDTO;

import java.sql.Connection;
import java.util.List;

public interface CustomerBO {

    String saveCustomer(CustomerDTO student, Connection connection)throws Exception;
    boolean deleteCustomer(String id, Connection connection)throws Exception;
    boolean updateCustomer(String id,CustomerDTO student,Connection connection)throws Exception;
    CustomerDTO getCustomer(String id,Connection connection)throws Exception;
    List<CustomerDTO> getAllCustomer(Connection connection)throws Exception;
}
