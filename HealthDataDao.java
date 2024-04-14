import java.sql.*;
import java.util.*;

public class HealthDataDao {

  public boolean createHealthData(HealthData healthData) {
    String sql = "INSERT INTO health_data (id, user_id, weight, height, steps, heart_rate, date) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getCon();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setInt(1, healthData.getId());
      stmt.setInt(2, healthData.getUserId());
      stmt.setDouble(3, healthData.getWeight());
      stmt.setDouble(4, healthData.getHeight());
      stmt.setInt(5, healthData.getSteps());
      stmt.setInt(6, healthData.getHeartRate());
      stmt.setDate(7, healthData.getDate()); // Assuming getDate() returns a java.sql.Date

      int rowsAffected = stmt.executeUpdate();
      return rowsAffected > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public HealthData getHealthDataById(int id) {
    String sql = "SELECT * FROM health_data WHERE id = ?";
    try (Connection conn = DatabaseConnection.getCon();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, id);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return new HealthData(rs.getInt("id"), rs.getInt("user_id"),
              rs.getDouble("weight"), rs.getDouble("height"),
              rs.getInt("steps"), rs.getInt("heart_rate"),
              rs.getDate("date"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<HealthData> getHealthDataByUserId(int user_id) {
    List<HealthData> dataList = new ArrayList<>();
    String sql = "SELECT * FROM health_data WHERE user_id = ?";
    try (Connection conn = DatabaseConnection.getCon();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, user_id);
      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          dataList.add(new HealthData(rs.getInt("id"), rs.getInt("user_id"),
              rs.getDouble("weight"), rs.getDouble("height"),
              rs.getInt("steps"), rs.getInt("heart_rate"),
              rs.getDate("date")));
        }
      }
      return dataList;
    } catch (SQLException e) {
      e.printStackTrace();
      return dataList;
    }
  }

  public boolean updateHealthData(HealthData healthData) {
    String sql = "UPDATE health_data SET weight = ?, height = ?, steps = ?, heart_rate = ?, date = ? WHERE id = ?";
    try (Connection conn = DatabaseConnection.getCon();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setDouble(1, healthData.getWeight());
      stmt.setDouble(2, healthData.getHeight());
      stmt.setInt(3, healthData.getSteps());
      stmt.setInt(4, healthData.getHeartRate());
      stmt.setDate(5, healthData.getDate());
      stmt.setInt(6, healthData.getId());
      int result = stmt.executeUpdate();
      return result > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean deleteHealthData(int id) {
    String sql = "DELETE FROM health_data WHERE id = ?";
    try (Connection conn = DatabaseConnection.getCon();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, id);
      int result = stmt.executeUpdate();
      return result > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
