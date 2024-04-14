public class MedicineReminder {
    private int id;
    private int user_id;
    private String medicineName;
    private String dosage;
    private String schedule; // Consider using a more structured format or class for schedule
    private String startDate; // Consider using LocalDate for date handling
    private String endDate; // Consider using LocalDate for date handling

    // Constructor
    public MedicineReminder(int id, int user_id, String medicineName, String dosage,
            String schedule, String startDate, String endDate) {
        this.id = id;
        this.user_id = user_id;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
