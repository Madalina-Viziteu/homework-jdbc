package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {

    private Connection conn = null;
    private static final String SELECT = "SELECT employeeId, email, firstName FROM employees WHERE email = ?";
    private static final String INSERT = "INSERT employees(employeeId, firstName, email) VALUES (?, ?,?)";
    private static final String UPDATE = "UPDATE employees SET email = ?, firstName = ? WHERE employeeId = ?";

    public Transaction() {
        conn = DatabaseUtils.getDatabaseConnection();
    }

    private void setAutoCommit(boolean isAutoCommit) {
        if (conn != null) {
            try {
                conn.setAutoCommit(isAutoCommit);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void commit() {
        if (conn != null) {
            try {
                conn.commit();
                System.out.println("COMMIT");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void rollback() {
        if (conn != null) {
            try {
                conn.rollback();
                System.out.println("ROLLBACK");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private int select(String email) {
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(SELECT);
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();
                boolean next = rs.next();
                if (next) {
                    int id = rs.getInt("employeeId");
                    String email2 = rs.getString("email");
                    String firstName = rs.getString("firstName");
                    System.out.println(id + ", " + email2 + ", " + firstName);
                    return id;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private boolean insert(int employeeId, String firstName, String email) {
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(INSERT);
                pstmt.setInt(1, employeeId);
                pstmt.setString(2, firstName);
                pstmt.setString(3,email);
                int ret = pstmt.executeUpdate();
                System.out.println("Insert return: " + (ret == 1 ? "OK" : "ERROR"));
                return ret == 1;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private boolean update(int id, String email, String firstName) {
        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(UPDATE);
                pstmt.setString(1, email);
                pstmt.setString(2, firstName);
                pstmt.setInt(3, id);
                int ret = pstmt.executeUpdate();
                System.out.println("Update return: " + (ret == 1 ? "OK" : "ERROR"));
                return ret == 1;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public static void transaction() {
        Transaction transaction = new Transaction();
        transaction.setAutoCommit(false);
        boolean status = transaction.insert(5, "Bill", "bill@gmail.com");
        if (status) {
            int id = transaction.select("john@gmail.com");
            status = transaction.update(id, "john@yahoo.com", "Jonn");
        }
        if (status) {
            transaction.commit();
        } else {
            transaction.rollback();
        }
        transaction.setAutoCommit(true);
    }
}


