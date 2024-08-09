package lk.ijse.possystem.DAO;

import lk.ijse.possystem.DTO.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{
    public static String SAVE_CUSTOMER = "INSERT INTO customer(id,name,address,salary) VALUES (?,?,?,?)";
    public static String UPDATE_CUSTOMER = " UPDATE customer set name = ? , address = ?, salary = ? where id = ? ";
    public static String GET_CUSTOMER = " select * from customer where id = ? ";
    public static String GET_ALL_CUSTOMER = " select * from customer ";
    public static String DELETE_CUSTOMER = " DELETE FROM customer WHERE id = ? ";

    @Override
    public String savaCustomer(CustomerDTO customerDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement(SAVE_CUSTOMER);
            ps.setString(1, customerDTO.getId());
            ps.setString(2, customerDTO.getName());
            ps.setString(3, customerDTO.getAddress());
            ps.setDouble(4, customerDTO.getSalary());

            if(ps.executeUpdate() != 0){
                return "Customer Save Successfully";
            }else {
                return "Failed to Save Customer";
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) throws SQLException {
        try {
            var ps = connection.prepareStatement(DELETE_CUSTOMER);
            ps.setString(1, id);
            return ps.executeUpdate() != 0;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean updateCustomer(String id, CustomerDTO customerDTO, Connection connection) throws SQLException {
        try {
            var ps = connection.prepareStatement(UPDATE_CUSTOMER);
            ps.setString(1, customerDTO.getName());
            ps.setString(2, customerDTO.getAddress());
            ps.setDouble(3, customerDTO.getSalary());
            ps.setString(4, id);
            return ps.executeUpdate() != 0;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public CustomerDTO getCustomer(String id, Connection connection) throws SQLException {
        try {
            CustomerDTO customerDTO = new CustomerDTO();
            var ps = connection.prepareStatement(GET_CUSTOMER);
            ps.setString(1, id);
            var rst = ps.executeQuery();
            while (rst.next()){
                customerDTO.setId(rst.getString("id"));
                customerDTO.setName(rst.getString("name"));
                customerDTO.setAddress(rst.getString("address"));
                customerDTO.setSalary(rst.getDouble("salary"));

            }
            return customerDTO;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer(Connection connection) throws SQLException {
        try {
            List<CustomerDTO> customerDTOList = new ArrayList<>();
            var ps = connection.prepareStatement(GET_ALL_CUSTOMER);
            var rst = ps.executeQuery();
            while (rst.next()){
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setId(rst.getString("id"));
                customerDTO.setName(rst.getString("name"));
                customerDTO.setAddress(rst.getString("address"));
                customerDTO.setSalary(rst.getDouble("salary"));

                customerDTOList.add(customerDTO);
            }
            return customerDTOList;
        }catch (Exception e){
            throw new SQLException(e.getMessage());
        }
    }
}
