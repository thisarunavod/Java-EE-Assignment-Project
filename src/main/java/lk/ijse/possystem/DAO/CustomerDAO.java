package lk.ijse.possystem.DAO;

import lk.ijse.possystem.DTO.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    String  savaCustomer(CustomerDTO customerDTO, Connection connection);
    boolean deleteCustomer(String id,Connection connection) throws SQLException;
    boolean updateCustomer(String id, CustomerDTO customerDTO,Connection connection) throws SQLException;
    CustomerDTO getCustomer(String id,Connection connection) throws SQLException;

    List<CustomerDTO> getAllCustomer(Connection connection) throws SQLException;
}
