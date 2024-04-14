import java.util.*;
import java.time.*;

public class HealthMonitoringApp {

    private static UserDao userDao = new UserDao();
    private static HealthDataDao healthDataDao = new HealthDataDao();
    // private static MedicineReminderManager medicineReminderManager = new
    // MedicineReminderManager();
    private static MedicineReminderDao medicineReminderDao = new MedicineReminderDao();
    private static DoctorPortalDao doctorPortalDao = new DoctorPortalDao();
    private static RecommendationSystem recommendationSystem = new RecommendationSystem();
    private static RecommendationsDao recommendationsDao = new RecommendationsDao();

    public static void main(String[] args) {
        testRegisterNewUser();
        testLoginUser();
        testAddHealthData();
        testGenerateRecommendations();
        testAddMedicineReminder();
        testGetRemindersForUser(10); // Example user_id //change the user_id number to test for different user
        testGetDueRemindersForUser(10); // Example user_id //change the user_id number to test for different user
        testDoctorPortal();
        testAddDoctorPatientAssociation();
    }

    private static void testRegisterNewUser() {
        User newUser = new User(10, "Ben", "Dover", "Ben@example.com",// change id when adding new user
                "password123456", false);// change to true ro false depending on if user is doctor or patient
        boolean success = userDao.createUser(newUser);
        System.out.println("Registration " + (success ? "successful" : "failed"));
    }

    private static void testLoginUser() {
        String userEmail = "Ben@example.com"; // Example email change for new users
        String userPassword = "password123456";
        boolean loginSuccess = loginUser(userEmail, userPassword);
        System.out.println(loginSuccess ? "Login Successful" : "Incorrect email or password. Please try again.");
    }

    public static boolean loginUser(String email, String password) {
        return userDao.verifyPassword(email, password);
    }

    private static void testAddHealthData() {
        HealthData data = new HealthData(10, 10, 80, 1.9, 17000, 77, java.sql.Date.valueOf("2024-04-01"));// update data when adding new user
// update information for each new user
        boolean success = healthDataDao.createHealthData(data);
        System.out.println("Adding health data was " + (success ? "successful" : "failed"));
    }

    private static void testGenerateRecommendations() {
        int user_id = 10; // Example user ID // update for each new user.

        // Fetch health data for this specific user
        List<HealthData> healthDataList = healthDataDao.getHealthDataByUserId(user_id);
        if (!healthDataList.isEmpty()) {
            // Obtain the latest health data record, assuming the list is correctly sorted
            HealthData latestHealthData = healthDataList.get(healthDataList.size() - 1);

            // Generate and display recommendations based on the latest health data
            List<String> recommendations = recommendationSystem.generateRecommendations(latestHealthData);
            System.out.println("Recommendations for User ID " + user_id + ":");

            recommendations.forEach(recommendation -> {
                System.out.println(recommendation);
                // Directly declare and initialize currentDate within the lambda to avoid scope
                // issues
                String currentDate = LocalDate.now().toString(); // Ensure this line is within the forEach
                // Log each recommendation to the database, including the current date
                boolean logged = recommendationsDao.addRecommendation(user_id, recommendation, currentDate);
                if (!logged) {
                    System.out.println("Failed to log recommendation to database.");
                }
            });
        } else {
            System.out.println("No health data found for user ID " + user_id);
        }
    }

    private static void testAddMedicineReminder() {
        // Assuming MedicineReminder has a matching constructor
        MedicineReminder reminder = new MedicineReminder(10, 10, "Retrolin", "150mg", "Daily at 10 AM",
                ("2024-01-01"), ("2024-12-31"));// update data when adding new user or medicine
        boolean success = medicineReminderDao.addMedicineReminder(reminder);
        System.out.println("Adding medicine reminder was " + (success ? "successful" : "failed"));
    }

    private static void testGetRemindersForUser(int user_id) {
        List<MedicineReminder> reminders = medicineReminderDao.getRemindersForUser(user_id);
        System.out.println("User " + user_id + " has " + reminders.size() + " reminders.");
    }

    private static void testGetDueRemindersForUser(int user_id) {
        String currentDate = LocalDate.now().toString(); // Get current date in YYYY-MM-DD format
        List<MedicineReminder> dueReminders = medicineReminderDao.getDueRemindersForUser(user_id, currentDate);
        System.out.println("User " + user_id + " has " + dueReminders.size() + " due reminders.");
    }

    private static void testAddDoctorPatientAssociation() {
        // Example: Dynamically fetch doctor and patient IDs
        // Fetch all doctors and patients - assuming these methods are implemented to
        // fetch specific roles
        List<User> doctors = userDao.getAllDoctors();
        List<User> patients = userDao.getAllPatients();

        if (!doctors.isEmpty() && !patients.isEmpty()) {
            int doctor_id = doctors.get(0).getId();
            int patient_id = patients.get(8).getId(); // Will stay at 0 till add in new patient. Only change when adding new patient

            // Try to add the doctor-patient association
            boolean associationAdded = doctorPortalDao.addDoctorPatientAssociation(doctor_id, patient_id);
            System.out.println("Association between Doctor ID " + doctor_id + " and Patient ID " + patient_id
                    + (associationAdded ? " was successfully added." : " failed to add."));

            // Fetch and display all patients for this doctor to verify the addition
            List<User> patientsOfDoctor = doctorPortalDao.getPatientsByDoctorId(doctor_id);
            if (!patientsOfDoctor.isEmpty()) {
                System.out.println("Doctor ID " + doctor_id + " has the following patients:");
                patientsOfDoctor.forEach(patient -> System.out.println("Patient ID: " + patient.getId() + ", Name: "
                        + patient.getFirstName() + " " + patient.getLastName()));
            } else {
                System.out.println("No patients found for Doctor ID " + doctor_id);
            }
        } else {
            System.out.println("No doctors or patients found to perform association test.");
        }
    }

    private static void testDoctorPortal() {
        // Example doctor ID for testing
        int doctor_id = 1;
        Doctor doctor = doctorPortalDao.getDoctorById(doctor_id);
        if (doctor != null) {
            System.out.println("Doctor found: " + doctor.getFirstName() + " " + doctor.getLastName());
            // Extend this to fetch associated patients and their health data
        } else {
            System.out.println("Doctor not found.");
        }
    }
}
