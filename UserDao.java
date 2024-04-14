import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public boolean createUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email, password, is_doctor) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, hashedPassword);
            stmt.setBoolean(5, user.isDoctor());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("first_name"),
                            rs.getString("last_name"), rs.getString("email"),
                            rs.getString("password"), rs.getBoolean("is_doctor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("first_name"),
                            rs.getString("last_name"), rs.getString("email"),
                            rs.getString("password"), rs.getBoolean("is_doctor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, is_doctor = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            stmt.setBoolean(5, user.isDoctor());
            stmt.setInt(6, user.getId());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
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

    public boolean verifyPassword(String email, String password) {
        User user = getUserByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    public List<User> getAllDoctors() {
        List<User> doctors = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE is_doctor = TRUE";
        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                doctors.add(new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("email"), rs.getString("password"), rs.getBoolean("is_doctor")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public List<User> getAllPatients() {
        List<User> patients = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE is_doctor = FALSE";
        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                patients.add(new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("email"), rs.getString("password"), rs.getBoolean("is_doctor")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

}
