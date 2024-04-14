import java.sql.Date;

public class HealthData {
    private int id;
    private int user_id;
    private double weight;
    private double height;
    private int steps;
    private int heartRate;
    private Date date; // Consider using a date type like LocalDate for better date handling

    // Constructor
    public HealthData(int id, int user_id, double weight, double height, int steps, int heartRate, Date date) {
        this.id = id;
        this.user_id = user_id;
        this.weight = weight;
        this.height = height;
        this.steps = steps;
        this.heartRate = heartRate;
        this.date = date;
    }

    // BMI Calculation Method
    public double calculateBMI() {
        return weight / (height * height);
    }

    // Recommendation based on BMI
    public String getBMIRecommendation() {
        double bmi = calculateBMI();
        if (bmi < 18.5) {
            return "Your BMI is below the normal range. Consider nutritional consultation to reach a healthy weight.";
        } else if (bmi >= 18.5 && bmi < 25) {
            return "Your BMI is within the normal range. Continue maintaining your current lifestyle!";
        } else if (bmi >= 25 && bmi < 30) {
            return "Your BMI indicates overweight. Consider regular exercise and dietary adjustments.";
        } else {
            return "Your BMI falls within the obesity range. It's important to consult a healthcare provider for guidance.";
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return user_id;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getSteps() {
        return steps;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public Date getDate() {
        return date;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
