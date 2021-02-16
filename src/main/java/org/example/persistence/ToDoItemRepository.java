package org.example.persistence;

import org.example.config.DatabaseConfig;
import org.example.domain.ToDoItem;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ToDoItemRepository {

    public void createToDoItem(String description, LocalDateTime deadline) throws SQLException, IOException, ClassNotFoundException {

        String insertSql = "INSERT INTO to_do_item (description, deadline) VALUES (?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)
        ) {
            preparedStatement.setString(1, description);
            preparedStatement.setDate(2, java.sql.Date.valueOf(deadline.toLocalDate()));

            preparedStatement.executeUpdate();
        }
    }

    public List<ToDoItem> getToDoItems() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT id, description, deadline, done FROM to_do_item";

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            List<ToDoItem> toDoItems = new ArrayList<>();

            while (resultSet.next()) {
                ToDoItem item = new ToDoItem();
                item.setId(resultSet.getLong("id"));
                item.setDescription(resultSet.getString("description"));
                item.setDeadline(resultSet.getDate("deadline").toLocalDate().atStartOfDay());
                item.setDone(resultSet.getBoolean("done"));

                toDoItems.add(item);
            }

            return toDoItems;
        }
    }

    public void deleteToDoItem(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM to_do_item WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }
}
