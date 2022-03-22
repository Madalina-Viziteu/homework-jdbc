package project.repository;

import project.DatabaseUtils;

import java.sql.*;

public class EmployeeRepository {

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    public void findAllEmployees() {
        try {
            connection = DatabaseUtils.getDatabaseConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT employeeId, email, lastName FROM employees");
            while (rs.next()) {
                int id = rs.getInt("employeeId");
                String email = rs.getString("email");
                String lastName = rs.getString("lastName");
                System.out.println(id + ", " + email + ", " + lastName);
            }

//            rs.close();
//            stmt.close();
//            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Display all employees with names starting with the letter J (employeeId, firstName, lastName)
    public void displayAllEmployeesStartingWithLetter(String letter) {
        try {
            connection = DatabaseUtils.getDatabaseConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT employeeId, firstName, lastName FROM employees Where firstName like '" + letter + "%'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("employeeId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                System.out.println(id + ", " + firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  Display all employees that haven’t been assigned to a department
    public void displayAllEmployeesWithoutDepartment() {
        try {
            connection = DatabaseUtils.getDatabaseConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT employeeId, firstname, lastname FROM employees WHERE departmentId is NULL");
            while (rs.next()) {
                int id = rs.getInt("employeeId");
                String firstname = rs.getString("firstName");
                String lastname = rs.getString("lastName");
                System.out.println(id + ", " + firstname + ", " + lastname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  Display all employees along with the department they’re in
    public void displayAllEmployeesWithDepartment() {
        try {
            connection = DatabaseUtils.getDatabaseConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select employeeId, firstName, lastName, employees.departmentId, name from departments, employees where employees.departmentId = departments.departmentId;");
            while (rs.next()) {
                int id = rs.getInt("employeeId");
                String firstname = rs.getString("firstName");
                String lastname = rs.getString("lastName");
                Date dateOfBirth = rs.getDate("dateOfBirth");
                String departmentId = rs.getString("departmentId");
                String name = rs.getString("name");
                System.out.println(id + ", " + firstname + ", " + lastname + ", " + dateOfBirth + ", " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



