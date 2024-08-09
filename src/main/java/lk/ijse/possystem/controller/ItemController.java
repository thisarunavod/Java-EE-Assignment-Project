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
import lk.ijse.possystem.BO.ItemBO;
import lk.ijse.possystem.BO.ItemBOImpl;
import lk.ijse.possystem.DTO.CustomerDTO;
import lk.ijse.possystem.DTO.ItemDTO;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(urlPatterns = "/item",loadOnStartup = 3)
public class ItemController extends HttpServlet {

    Connection connection ;

    @Override
    public void init() throws ServletException {

        try {

            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/POSSystem");
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
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
            var itemBO = new ItemBOImpl();

            //Save data in the DB
            writer.write(itemBO.saveItem(itemDTO,connection));
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
            ItemBO itemBO = new ItemBOImpl();
            List<ItemDTO> allItems = itemBO.getAllItems(connection);
            jsonb.toJson(allItems,writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            var itemBO = new ItemBOImpl();
            /*var studentId = req.getParameter("id");*/
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

            if(itemBO.updateItem(itemDTO.getCode(),itemDTO,connection)){
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
            var itemBO = new ItemBOImpl();
            /*var studentId = req.getParameter("id");*/
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

            if(itemBO.deleteItem(itemDTO.getCode(),connection)){
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
