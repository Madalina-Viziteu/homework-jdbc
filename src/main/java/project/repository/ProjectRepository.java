package project.repository;

import project.DatabaseUtils;

import java.sql.*;

public class ProjectRepository {

    private Statement statement;
    private Connection connection;


    public void displayAllProjects() {
        try {
            String display = "SELECT * FROM projects";
            connection = DatabaseUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(display);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("projectId");
                String description = rs.getString("description");
                System.out.println(id + ", " + description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
