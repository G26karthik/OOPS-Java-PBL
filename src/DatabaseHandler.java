import java.sql.*;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/mental_health_db";
    private static final String USER = "root";  // Adjust your username
    private static final String PASSWORD = "Saitan26";  // Adjust your password

    public Connection connect() {
        try {
            // Load MySQL JDBC Driver (if necessary)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            return null;
        }
    }

    public void saveResponse(String userName, int depressionScore, int anxietyScore, int stressScore) {
        String query = "INSERT INTO responses (username, depression_score, anxiety_score, stress_score) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn != null ? conn.prepareStatement(query) : null) {
            if (pstmt != null) {
                pstmt.setString(1, userName);
                pstmt.setInt(2, depressionScore);
                pstmt.setInt(3, anxietyScore);
                pstmt.setInt(4, stressScore);
                pstmt.executeUpdate();
            } else {
                System.out.println("PreparedStatement creation failed due to null connection.");
            }
        } catch (SQLException e) {
            System.out.println("Error saving response: " + e.getMessage());
        }
    }
}

