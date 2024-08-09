package lk.ijse.possystem.DAO;

import lk.ijse.possystem.DTO.CustomerDTO;
import lk.ijse.possystem.DTO.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public sealed interface ItemDAO permits ItemDAOImpl {
    String  savaItem(ItemDTO itemDTO, Connection connection);
    boolean deleteItem(String code,Connection connection) throws SQLException;
    boolean updateItem(String code, ItemDTO itemDTO,Connection connection) throws SQLException;
    CustomerDTO getItem(String code,Connection connection) throws SQLException;

    List<ItemDTO> getAllItem(Connection connection) throws SQLException;
}
