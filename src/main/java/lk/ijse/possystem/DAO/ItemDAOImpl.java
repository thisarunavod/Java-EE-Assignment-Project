package lk.ijse.possystem.DAO;

import lk.ijse.possystem.DTO.CustomerDTO;
import lk.ijse.possystem.DTO.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ItemDAOImpl implements ItemDAO {
    public static String SAVE_ITEM = "INSERT INTO item(code,name,qty,price) VALUES (?,?,?,?)";
    public static String UPDATE_ITEM = " UPDATE item set name = ? , qty = ?, price = ? where code = ? ";
    public static String GET_ITEM = " select * from item where code = ? ";
    public static String GET_ALL_ITEM = " select * from item ";
    public static String DELETE_ITEM = " DELETE FROM item WHERE code = ? ";
    @Override
    public String savaItem(ItemDTO itemDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(SAVE_ITEM);
            ps.setString(1, itemDTO.getCode());
            ps.setString(2, itemDTO.getName());
            ps.setDouble(3, itemDTO.getQty());
            ps.setDouble(4, itemDTO.getPrice());

            if(ps.executeUpdate() != 0){
                return "Item Save Successfully";
            }else {
                return "Failed to Save Item";
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteItem(String code, Connection connection) throws SQLException {
        try {
            var ps = connection.prepareStatement(DELETE_ITEM);
            ps.setString(1, code);
            return ps.executeUpdate() != 0;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean updateItem(String code, ItemDTO itemDTO, Connection connection) throws SQLException {
        try {
            var ps = connection.prepareStatement(UPDATE_ITEM);
            ps.setString(1, itemDTO.getName());
            ps.setDouble(2, itemDTO.getQty());
            ps.setDouble(3, itemDTO.getPrice());
            ps.setString(4, code);
            return ps.executeUpdate() != 0;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public CustomerDTO getItem(String code, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public List<ItemDTO> getAllItem(Connection connection) throws SQLException {
        try {
            List<ItemDTO> itemDTOList = new ArrayList<>();
            var ps = connection.prepareStatement(GET_ALL_ITEM);
            var rst = ps.executeQuery();
            while (rst.next()){
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setCode(rst.getString("code"));
                itemDTO.setName(rst.getString("name"));
                itemDTO.setQty(rst.getDouble("qty"));
                itemDTO.setPrice(rst.getDouble("price"));

                itemDTOList.add(itemDTO);
            }
            return itemDTOList;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }
}
