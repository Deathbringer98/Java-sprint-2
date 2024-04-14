import java.util.*;
import java.sql.*;

public class DoctorPortalDao {
    private UserDao userDao;
    private HealthDataDao healthDataDao;

    public DoctorPortalDao() {
        this.userDao = new UserDao();
        this.healthDataDao = new HealthDataDao();
    }

    public Doctor getDoctorById(int doctor_id) {
        User user = userDao.getUserById(doctor_id);
        if (user != null && user.isDoctor()) {
            return convertUserToDoctor(user);
        }
        return null;
    }

    // Fetches all patients for a given doctor
    public List<User> getPatientsByDoctorId(int doctor_id) {
        List<User> patients = new ArrayList<>();
        String sql = "SELECT patient_id FROM doctor_patient WHERE doctor_id = ?";
        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctor_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int patient_id = rs.getInt("patient_id");
                User patient = userDao.getUserById(patient_id);
                if (patient != null) {
                    patients.add(patient);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public List<HealthData> getHealthDataByPatientId(int patient_id) {
        return healthDataDao.getHealthDataByUserId(patient_id);
    }

    private Doctor convertUserToDoctor(User user) {
        return new Doctor(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                user.isDoctor(), "Default License", "Default Specialization", 0,
                "Default Hospital", "Default Hours", "Default Degree", "Default School",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public List<User> getAllDoctors() {
        return userDao.getAllDoctors();
    }

    public List<User> getAllPatients() {
        return userDao.getAllPatients();
    }

    // Method to add a doctor-patient relationship
    public boolean addDoctorPatientAssociation(int doctor_id, int patient_id) {
        String sql = "INSERT INTO doctor_patient (doctor_id, patient_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getCon();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctor_id);
            stmt.setInt(2, patient_id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
