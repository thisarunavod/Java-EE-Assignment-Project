package lk.ijse.possystem.BO;

import lk.ijse.possystem.DAO.CustomerDAO;
import lk.ijse.possystem.DAO.CustomerDAOImpl;
import lk.ijse.possystem.DTO.CustomerDTO;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class CustomerBOImpl implements CustomerBO{
    @Override
    public String saveCustomer(CustomerDTO customerDTO, Connection connection) throws Exception {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.savaCustomer(customerDTO,connection);
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) throws Exception {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.deleteCustomer(id,connection);
    }

    @Override
    public boolean updateCustomer(String id, CustomerDTO customerDTO, Connection connection) throws Exception {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.updateCustomer(id,customerDTO,connection);
    }

    @Override
    public CustomerDTO getCustomer(String id, Connection connection) throws Exception {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.getCustomer(id,connection);
    }

    @Override
    public List<CustomerDTO> getAllCustomer(Connection connection) throws Exception {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.getAllCustomer(connection);
    }
}
