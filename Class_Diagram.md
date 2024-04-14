+----------------+        +-------------------+       +---------------+
|     User       |        |   DoctorPatient   |       |   HealthData  |
+----------------+        +-------------------+       +---------------+
| id             |<---+   | id                |       | id            |
| first_name     |    |   | doctor_id         |---+   | user_id       |--+
| last_name      |    |   | patient_id        |   |   | record_date   |  |
| email          |    |   +-------------------+   |   | data_type     |  |
+----------------+    |                          |   | value         |  |
   ^                  |                          +-->| notes         |  |
   |                  |                              +---------------+  |
   |                  |                                                    |
   |                  |       +-----------------------+                   |
   |                  +------>|   MedicineReminders   |                   |
   |                          +-----------------------+                   |
   |                          | id                    |                   |
   |                          | user_id               |-------------------+
   |                          | reminder_time         |
   |                          | medicine_name         |
   +--------------------------| notes                 |
                              +-----------------------+

                              +-----------------------+
                              |   Recommendations     |
                              +-----------------------+
                              | id                    |
                              | user_id               |-------------------+
                              | recommendation_text   |                   |
                              | created_at            |                   |
                              +-----------------------+                   |
                                                                            |
                                      Legend                                |
                                      ------                                |
                                      |---| One to Many Relationship        |
                                      |-->| One to One Relationship         |
                                                                            +
