### Deployment Documentation for Health Monitoring App

#### Overview

This document serves as the installation manual for the Health Monitoring App, which helps users monitor and manage their health data efficiently. The application stores medical records, sets medicine reminders, and offers health recommendations based on user data.

#### System Requirements

- **Operating System**: Windows 7 or higher, macOS X 10.10 or higher, Linux (Ubuntu, Fedora, etc.)
- **Java**: Java Runtime Environment (JRE) version 1.8 or higher.
- **Database**: PostgreSQL 9.6 or higher.
- **Memory**: Minimum 512 MB RAM.
- **Disk Space**: Minimum 50 MB available disk space for installation.
- **Network**: Internet connection for installation and operation.

#### Prerequisites

1. **Java Installation**:
   - Ensure Java is installed on your system. You can download it from [Oracle's official website](https://www.oracle.com/java/technologies/javase-jre8-downloads.html).
   - Verify the installation by opening a command prompt or terminal and typing:
     ```bash
     java -version
     ```
     This should display the installed Java version.

2. **PostgreSQL Installation**:
   - Install PostgreSQL if it's not already installed. [Download here](https://www.postgresql.org/download/).
   - During installation, set up the initial username and password for the database server.

#### Installation Steps

1. **Download the Application**:
   - Download the latest version of the Health Monitoring App from the official repository or website.
   - This might include downloading a `.zip` or `.tar.gz` file.

2. **Extract the Files**:
   - Extract the downloaded archive to your desired location:
     ```bash
     unzip path_to_zip_file -d destination_folder
     ```
   - For Windows, you can use software like WinRAR or 7-Zip to extract the files.

3. **Database Setup**:
   - Open your PostgreSQL command line client (psql). Log in using:
     ```bash
     psql -U postgres
     ```
   - Create the necessary databases and tables by running the SQL scripts included in the `script` folder:
     ```bash
     \i path_to_script\Users.sql
     \i path_to_script\doctor_patient.sql
     \i path_to_script\health_data.sql
     \i path_to_script\medicine_reminders.sql
     \i path_to_script\recommendations.sql
     ```

4. **Configure the Application**:
   - Navigate to the installation directory.
   - Open the `config.properties` file in a text editor.
   - Modify the database settings to match your setup:
     ```
     database.url=jdbc:postgresql://localhost:5432/your_database_name
     database.user=your_username
     database.password=your_password
     ```
   - Save the changes.

#### Running the Application

- Open a command prompt or terminal window.
- Navigate to the installation directory.
- Run the application using:
  ```bash
  java -cp ".;lib/*" HealthMonitoringApp
  ```
- Follow the on-screen instructions to start using the application.

#### Troubleshooting

- **Java Not Found**: Ensure that Java is installed and that your `PATH` environment variable includes the Java `bin` directory.
- **Database Connection Issues**: Check that PostgreSQL is running and that all database settings in `config.properties` are correct.
- **Missing Libraries**: Ensure all necessary `.jar` files are in the `lib` directory.

### Author: Matthew Menchinton 
### Date: 2024-04-14
### Copyright  (c) 2024 Matthew Menchinton
### License  MIT