package org.example.web;

import org.example.config.ObjectMapperConfig;
import org.example.domain.ToDoItem;
import org.example.service.ToDoItemService;
import org.example.transfer.CreateItemRequest;
import org.example.transfer.UpdateItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/to-do-items")
public class ToDoItemServlet extends HttpServlet {

    private ToDoItemService toDoItemService = new ToDoItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CreateItemRequest request = ObjectMapperConfig.getObjectMapper().readValue(req.getReader(), CreateItemRequest.class);

        try {
            toDoItemService.createToDoItem(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<ToDoItem> toDoItems = toDoItemService.getToDoItems();

            String responseJson = ObjectMapperConfig.getObjectMapper().writeValueAsString(toDoItems);

            resp.getWriter().print(responseJson);
            resp.getWriter().flush();
            resp.getWriter().close();

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        UpdateItemRequest request = ObjectMapperConfig.getObjectMapper().readValue(req.getReader(), UpdateItemRequest.class);

        try {
            toDoItemService.updateToDoItem(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal server error: " + e.getMessage());
        }
    }
}
