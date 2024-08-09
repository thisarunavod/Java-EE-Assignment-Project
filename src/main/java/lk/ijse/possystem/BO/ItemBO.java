package lk.ijse.possystem.BO;

import lk.ijse.possystem.DTO.CustomerDTO;
import lk.ijse.possystem.DTO.ItemDTO;

import java.sql.Connection;
import java.util.List;

public interface ItemBO {
    String saveItem(ItemDTO itemDTO, Connection connection)throws Exception;
    boolean deleteItem(String code, Connection connection)throws Exception;
    boolean updateItem(String code,ItemDTO itemDTO,Connection connection)throws Exception;
    CustomerDTO getItem(String code,Connection connection)throws Exception;
    List<ItemDTO> getAllItems(Connection connection)throws Exception;
}
