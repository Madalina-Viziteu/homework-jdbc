package project.repository;

import project.DatabaseUtils;
import project.entity.Department;
import java.sql.*;
import java.util.ArrayList;


public class DepartmentRepository {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedstatement;

    public ArrayList<Department> findAll() {
        ArrayList<Department> departments = null;
        try {
            departments = new ArrayList<>();
            connection = DatabaseUtils.getDatabaseConnection();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from departments");
            while (rs.next()) {
                int departmentId = rs.getInt(1);
                String name = rs.getString(2);
                Department department = new Department(departmentId, name);
                departments.add(department);
                System.out.println(departments);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return departments;
    }


    public Department findById(Integer departmentId) {
        try {
            String select = "select * from departments where departmentId = ?";
            connection = DatabaseUtils.getDatabaseConnection();
            PreparedStatement stmt = connection.prepareStatement(select);
            stmt.setInt(1, departmentId);
            ResultSet rs = stmt.executeQuery();
            boolean next = rs.next();
            Department department = null;
            if (next) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                department = new Department(id, name);
            }
            System.out.println(department);
        } catch (SQLException x) {
            x.printStackTrace();
        }
        return null;
    }

    public void deleteById(Integer departmentId) {
        try {
            String select = "delete from departments where departmentId = ?";
            String updateEmployees = "update employees set departmentId = null where departmentId = ?";
            connection = DatabaseUtils.getDatabaseConnection();
            preparedstatement = connection.prepareStatement(updateEmployees);
            preparedstatement.setInt(1, departmentId);
            preparedstatement.executeUpdate();
            PreparedStatement stmt = connection.prepareStatement(select);
            stmt.setInt(1, departmentId);
            stmt.executeUpdate();
            System.out.println("The department with id" + departmentId + " was deleted");

        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    public void insertDepartment(Department department) {
        String insert = "INSERT INTO departments  VALUES(?,?)";
        try {
            connection = DatabaseUtils.getDatabaseConnection();
            preparedstatement = connection.prepareStatement(insert);
            preparedstatement.setInt(1, department.getDepartmentId());
            preparedstatement.setString(2, department.getName());
            int ret = preparedstatement.executeUpdate();
            System.out.println("Insert return: " + (ret == 1 ? "Department added" : "ERROR"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateDepartment(Department department) {
        String update = "UPDATE departments SET name  = ? WHERE departmentId = 3";
        try {
            connection = DatabaseUtils.getDatabaseConnection();
            preparedstatement = connection.prepareStatement(update);
            preparedstatement.setString(1, department.getName());
            int ret = preparedstatement.executeUpdate();
            System.out.println("Update return: " + (ret == 1 ? "Department updated" : "ERROR"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


