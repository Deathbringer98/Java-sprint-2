import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {
    private String medicalLicenseNumber;
    private String specialization;
    private int yearsOfExperience;
    private String hospitalAffiliation;
    private String availableHours;
    private String degree;
    private String medicalSchool;
    private List<String> boardCertifications = new ArrayList<>();
    private List<String> researchInterests = new ArrayList<>();
    private List<String> publications = new ArrayList<>();

    // Full constructor
    public Doctor(int id, String first_name, String last_name, String email, String password,
            boolean is_doctor, String medicalLicenseNumber, String specialization, int yearsOfExperience,
            String hospitalAffiliation, String availableHours, String degree, String medicalSchool,
            List<String> boardCertifications, List<String> researchInterests,
            List<String> publications, Object object, Object object2) {
        super(id, first_name, last_name, email, password, is_doctor);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.hospitalAffiliation = hospitalAffiliation;
        this.availableHours = availableHours;
        this.degree = degree;
        this.medicalSchool = medicalSchool;
        this.boardCertifications = boardCertifications;
        this.researchInterests = researchInterests;
        this.publications = publications;
    }

    // Getters and Setters
    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getHospitalAffiliation() {
        return hospitalAffiliation;
    }

    public void setHospitalAffiliation(String hospitalAffiliation) {
        this.hospitalAffiliation = hospitalAffiliation;
    }

    public String getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(String availableHours) {
        this.availableHours = availableHours;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMedicalSchool() {
        return medicalSchool;
    }

    public void setMedicalSchool(String medicalSchool) {
        this.medicalSchool = medicalSchool;
    }

    public List<String> getBoardCertifications() {
        return boardCertifications;
    }

    public void setBoardCertifications(List<String> boardCertifications) {
        this.boardCertifications = boardCertifications;
    }

    public List<String> getResearchInterests() {
        return researchInterests;
    }

    public void setResearchInterests(List<String> researchInterests) {
        this.researchInterests = researchInterests;
    }

    public List<String> getPublications() {
        return publications;
    }

    public void setPublications(List<String> publications) {
        this.publications = publications;
    }

    // Example Methods
    public void addPublication(String publication) {
        this.publications.add(publication);
    }

    public boolean isSpecializedIn(String field) {
        return this.specialization.equalsIgnoreCase(field);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + getId() +
                ", first_name='" + getFirstName() + '\'' +
                ", last_name='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", medicalLicenseNumber='" + medicalLicenseNumber + '\'' +
                ", specialization='" + specialization + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                ", hospitalAffiliation='" + hospitalAffiliation + '\'' +
                ", availableHours='" + availableHours + '\'' +
                ", degree='" + degree + '\'' +
                ", medicalSchool='" + medicalSchool + '\'' +
                ", boardCertifications=" + boardCertifications +
                ", researchInterests=" + researchInterests +
                ", publications=" + publications +
                '}';
    }
}
