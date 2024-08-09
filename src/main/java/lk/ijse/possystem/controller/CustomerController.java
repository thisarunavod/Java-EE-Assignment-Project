package lk.ijse.possystem.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.possystem.BO.CustomerBO;
import lk.ijse.possystem.BO.CustomerBOImpl;
import lk.ijse.possystem.DTO.CustomerDTO;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/customer",loadOnStartup = 3)
public class CustomerController  extends HttpServlet {
    Connection connection;

    @Override
    public void init() throws ServletException {
        try {

            var ctx = new InitialContext();   // connection pool eke connection ekak balala aragena tya ganna tana //
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/POSSystem"); //look up method ekata pluwn api resources ekaka name ekak dunnama eeke dewal ganna //
            this.connection = pool.getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }
        try (var writer = resp.getWriter()){

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            var customerBO = new CustomerBOImpl();

            //Save data in the DB
            writer.write(customerBO.saveCustomer(customerDTO,connection));
            resp.setStatus(HttpServletResponse.SC_CREATED);


        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            resp.setContentType("application/json");
            /*Jsonb jsonb = JsonbBuilder.create();
            CustomerBO customerBO = new CustomerBOImpl();
            CustomerDTO customerDTO = customerBO.getCustomer("1", connection);
            jsonb.toJson(customerDTO,writer);*/

            Jsonb jsonb = JsonbBuilder.create();
            CustomerBO customerBO = new CustomerBOImpl();
            List<CustomerDTO> allCustomer = customerBO.getAllCustomer(connection);
            jsonb.toJson(allCustomer,writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            var customerBO = new CustomerBOImpl();
            /*var studentId = req.getParameter("id");*/
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

            if(customerBO.updateCustomer(customerDTO.getId(),customerDTO,connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                writer.write("Update failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (var writer = resp.getWriter()) {
            var customerBO = new CustomerBOImpl();
            /*var studentId = req.getParameter("id");*/
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

            if(customerBO.deleteCustomer(customerDTO.getId(),connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else {
                writer.write("Delete failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
