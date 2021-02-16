package org.example.persistence;

import org.example.config.DatabaseConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
}
