import java.util.*;
import java.time.*;

public class MedicineReminderManager {
    private List<MedicineReminder> reminders;

    public MedicineReminderManager() {
        this.reminders = new ArrayList<>();
    }

    public boolean addReminder(MedicineReminder reminder) {
        // Attempt to add the reminder to the list
        boolean isAdded = reminders.add(reminder);

        // Return true if the reminder was successfully added
        return isAdded;
    }

    // Retrieves all reminders for a specific user
    public List<MedicineReminder> getRemindersForUser(int user_id) {
        List<MedicineReminder> userReminders = new ArrayList<>();
        for (MedicineReminder reminder : reminders) {
            if (reminder.getUserId() == user_id) {
                userReminders.add(reminder);
            }
        }
        return userReminders;
    }

    // Retrieves reminders that are due for a specific user
    public List<MedicineReminder> getDueReminders(int user_id) {
        List<MedicineReminder> dueReminders = new ArrayList<>();
        LocalDate now = LocalDate.now();

        for (MedicineReminder reminder : reminders) {
            if (reminder.getUserId() == user_id) {
                LocalDate startDate = LocalDate.parse(reminder.getStartDate());
                LocalDate endDate = LocalDate.parse(reminder.getEndDate());
                if ((now.isEqual(startDate) || now.isAfter(startDate))
                        && (now.isBefore(endDate) || now.isEqual(endDate))) {
                    dueReminders.add(reminder);
                }
            }
        }
        return dueReminders;
    }
}
