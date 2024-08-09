package lk.ijse.possystem.BO;

import lk.ijse.possystem.DAO.ItemDAO;
import lk.ijse.possystem.DAO.ItemDAOImpl;
import lk.ijse.possystem.DTO.CustomerDTO;
import lk.ijse.possystem.DTO.ItemDTO;

import java.sql.Connection;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    @Override
    public String saveItem(ItemDTO itemDTO, Connection connection) throws Exception {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.savaItem(itemDTO,connection);
    }

    @Override
    public boolean deleteItem(String code, Connection connection) throws Exception {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.deleteItem(code,connection);
    }

    @Override
    public boolean updateItem(String code, ItemDTO itemDTO, Connection connection) throws Exception {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.updateItem(code,itemDTO,connection);
    }

    @Override
    public CustomerDTO getItem(String code, Connection connection) throws Exception {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItems(Connection connection) throws Exception {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.getAllItem(connection);
    }
}
