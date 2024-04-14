import java.sql.*;
import java.util.*;

public class MedicineReminderDao {

    // Method to add a new medicine reminder to the database
    public boolean addMedicineReminder(MedicineReminder reminder) {
        String sql = "INSERT INTO medicine_reminders (user_id, medicine_name, dosage, schedule, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reminder.getUserId());
            stmt.setString(2, reminder.getMedicineName());
            stmt.setString(3, reminder.getDosage());
            stmt.setString(4, reminder.getSchedule());
            stmt.setDate(5, java.sql.Date.valueOf(reminder.getStartDate())); // Ensure dates are converted correctly
            stmt.setDate(6, java.sql.Date.valueOf(reminder.getEndDate()));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve all reminders for a specific user
    public List<MedicineReminder> getRemindersForUser(int user_id) {
        List<MedicineReminder> reminders = new ArrayList<>();
        String sql = "SELECT * FROM medicine_reminders WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reminders.add(new MedicineReminder(
                        rs.getInt("id"),
                        user_id,
                        rs.getString("medicine_name"),
                        rs.getString("dosage"),
                        rs.getString("schedule"),
                        rs.getDate("start_date").toString(),
                        rs.getDate("end_date").toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reminders;
    }

    // Method to retrieve due reminders for a user based on the current date
    public List<MedicineReminder> getDueRemindersForUser(int user_id, String currentDate) {
        List<MedicineReminder> dueReminders = new ArrayList<>();
        String sql = "SELECT * FROM medicine_reminders WHERE user_id = ? AND end_date >= ?";
        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user_id);
            stmt.setDate(2, java.sql.Date.valueOf(currentDate)); // Converting string to sql.Date
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (java.sql.Date.valueOf(currentDate).before(rs.getDate("end_date"))) {
                    dueReminders.add(new MedicineReminder(
                            rs.getInt("id"),
                            user_id,
                            rs.getString("medicine_name"),
                            rs.getString("dosage"),
                            rs.getString("schedule"),
                            rs.getDate("start_date").toString(),
                            rs.getDate("end_date").toString()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dueReminders;
    }
}
