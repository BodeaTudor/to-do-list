package org.example.service;

import org.example.domain.ToDoItem;
import org.example.persistence.ToDoItemRepository;
import org.example.transfer.CreateItemRequest;
import org.example.transfer.UpdateItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ToDoItemService {

    private ToDoItemRepository toDoItemRepository = new ToDoItemRepository();

    public void createToDoItem(CreateItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating toDoItem: " + request);

        toDoItemRepository.createToDoItem(request.getDescription(), request.getDeadline());
    }

    public List<ToDoItem> getToDoItems() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving toDoItems...");

        return toDoItemRepository.getToDoItems();
    }

    public void deleteToDoItem(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting toDoItem: " + id);

        toDoItemRepository.deleteToDoItem(id);
    }

    public void updateToDoItem(long id, UpdateItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating toDoItem: " + id);

        toDoItemRepository.updateToDoItem(id, request.isDone());
    }
}
