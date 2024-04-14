import java.util.ArrayList;
import java.util.List;

public class RecommendationSystem {
        private static final int MIN_HEART_RATE = 60;
        private static final int MAX_HEART_RATE = 100;
        private static final int MIN_STEPS = 10000;

        public List<String> generateRecommendations(HealthData healthData) {
                List<String> recommendations = new ArrayList<>();

                // Analyze heart rate
                int heartRate = healthData.getHeartRate();
                if (heartRate < MIN_HEART_RATE) {
                        recommendations.add("Your heart rate is lower than the recommended range. " +
                                        "Consider increasing your physical activity to improve your cardiovascular health.");
                } else if (heartRate > MAX_HEART_RATE) {
                        recommendations.add("Your heart rate is higher than the recommended range. " +
                                        "Consider consulting a healthcare provider for a check-up.");
                }

                // Analyze steps
                int steps = healthData.getSteps();
                if (steps < MIN_STEPS) {
                        recommendations.add("You're not reaching the recommended daily step count of 10,000 steps. " +
                                        "Try to incorporate more walking or other physical activities into your daily routine.");
                } else {
                        recommendations.add(
                                        "Great job on reaching the recommended daily step count! Keep up the good work.");
                }

                // BMI Recommendations
                double bmi = calculateBMI(healthData.getWeight(), healthData.getHeight());
                recommendations.add(generateBMIRecommendation(bmi));

                return recommendations;
        }

        private double calculateBMI(double weight, double height) {
                return weight / (height * height);
        }

        private String generateBMIRecommendation(double bmi) {
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
}
