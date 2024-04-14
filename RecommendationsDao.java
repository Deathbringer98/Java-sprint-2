import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecommendationsDao {

    public boolean addRecommendation(int user_id, String recommendationText, String dateStr) {
        String sql = "INSERT INTO recommendations (user_id, recommendation_text, date) VALUES (?, ?, ?::date)";
        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user_id);
            stmt.setString(2, recommendationText);
            stmt.setString(3, dateStr); // Assuming dateStr is in the format YYYY-MM-DD

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> generateRecommendations(int user_id) {
        List<String> recommendations = new ArrayList<>();
        // Example: Fetch health data for the user
        String sql = "SELECT * FROM health_data WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user_id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Analyze the fetched data
                    double bmi = calculateBMI(rs.getDouble("weight"), rs.getDouble("height"));
                    // Generate recommendations based on the analysis
                    if (bmi < 18.5) {
                        recommendations.add("Consider nutritional consultation to reach a healthy weight.");
                    } else if (bmi >= 25) {
                        recommendations.add("Regular exercise and dietary adjustments are recommended.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recommendations;
    }

    private double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }
}
