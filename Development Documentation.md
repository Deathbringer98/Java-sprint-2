

### Development Documentation for Health Monitoring App

#### Javadocs Generation

To generate Javadocs for your project, you should use the following steps:

1. Open your command line interface (CLI).
2. Navigate to your project’s root directory:
   ```bash
   cd C:\Users\jacka\OneDrive\Desktop\School\Semester 3\Sprint 2\Java\Skeleton    ---"Example Directory"
   ```
3. Run the following command to generate Javadocs for all Java files in your project:
   ```bash
   javadoc -d doc -sourcepath . -subpackages .
   ```
   - `-d doc` specifies that the output directory for the Javadocs should be a folder named `doc` created at your project root.
   - `-sourcepath .` sets the current directory as the source path.
   - `-subpackages .` tells Javadoc to recursively generate documents for all subpackages.

Ensure the `doc` directory exists or the Javadoc command will create it for storing the documentation.

#### Source Code Directory Structure

The structure of your project is as follows:

- `lib/`: Contains external libraries needed for the project.
  - `jBCrypt-0.4.1.jar`: Library for password hashing.
  - `postgresql-42.6.0.jar`: PostgreSQL JDBC Driver for database connectivity.
- `script/`: Contains SQL scripts for database setup.
  - `Users.sql`, `doctor_patient.sql`, `health_data.sql`, `medicine_reminders.sql`, `recommendations.sql`
- `Class_Diagram.md`: Markdown file with the class diagram.
- `DatabaseConnection.java`, `Doctor.java`, `DoctorPortalDao.java`, `HealthData.java`, `HealthDataDao.java`, `HealthMonitoringApp.java`, `MedicineReminder.java`, `MedicineReminderDao.java`, `MedicineReminderManager.java`, `RecommendationSystem.java`, `RecommendationsDao.java`, `User.java`, `UserDao.java`: Java source files for the application.
- `.gitignore`: Git ignore file for excluding files from version control.
- `LICENSE`: Project license file.
- `User_Documentation.doc`: Documentation file for users.

#### Build Process

Here are the detailed instructions on how to compile and run your application:

1. **Compiling the Application**
   Use the following command to compile all Java source files:
   ```bash
   javac -cp "lib/*" *.java
   ```
   This command compiles all Java files in the root directory with the classpath set to include all JAR files in the `lib` directory.

2. **Running the Application**
   To run the application, use the following command:
   ```bash
   java -cp ".;lib/*" HealthMonitoringApp
   ```
   This sets the classpath to the current directory and the `lib` directory to include all compiled classes and libraries.

#### Compiler Time Dependencies

- **Java Development Kit (JDK)** - Version 1.8 or newer.
- **External Libraries**:
  - `jBCrypt-0.4.1.jar` (for password hashing)
  - `postgresql-42.6.0.jar` (for database connectivity)

#### Development Standards

- Use camelCase for naming variables and methods.
- Class names should be nouns in UpperCamelCase.
- Methods should be verbs in lowerCamelCase.
- Code commenting should be done extensively to maintain readability and ease of maintenance.
- Follow best coding practices and guidelines as per Java coding standards.

#### Database Setup for Development

1. **Database Initialization**:
   Run the SQL scripts located in the `script` folder to set up your database. These scripts will create all necessary tables and relationships:
   ```bash
   psql -U username -d databasename -a -f script/Users.sql
   psql -U username -d databasename -a -f script/doctor_patient.sql
   psql -U username -d databasename -a -f script/health_data.sql
   psql -U username -d databasename -a -f script/medicine_reminders.sql
   psql -U username -d databasename -a -f script/recommendations.sql
   ```
   Replace `username` and `databasename` with your PostgreSQL username and the name of your database.

#### Retrieving the Source Code

The source code can be cloned from your Git repository using:
```bash
git clone https://github.com/Deathbringer98/Java-sprint-2.git
```.