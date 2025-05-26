package ap.exercises.midTerm_project.version2_part2.model;

public class Librarian extends User {
    private String employeeId;
    private String phoneNumber;
    private String address;
    private String username;
    private String password;
    private int processedLoans;
    private String educationLevel;

    public Librarian(String firstName, String lastName, String employeeId, String username, String password, String educationLevel) {
        super(firstName, lastName);
        setEmployeeId(employeeId);
        setUsername(username);
        setPassword(password);
        setEducationLevel(educationLevel);
        this.phoneNumber = "";
        this.address = "";
        this.processedLoans = 0;
    }

    // Setters
    public void setEmployeeId(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be empty!");
        }
        this.employeeId = employeeId.trim();
    }

    public void setPhoneNumber(String phoneNumber) {// Check the number format
        if (phoneNumber != null && !phoneNumber.trim().isEmpty() && !phoneNumber.matches("^\\+?\\d{10,15}$")) {
            this.phoneNumber = ""; // Treat invalid phone number as empty
        } else {
            this.phoneNumber = phoneNumber != null ? phoneNumber.trim() : "";
        }
    }

    public void setAddress(String address) {
        this.address = address != null ? address.trim() : "";
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty() || username.length() < 4) {
            throw new IllegalArgumentException("Username must be at least 4 characters!");
        }
        this.username = username.trim();
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters!");
        }
        this.password = password;
    }

    public void setEducationLevel(String educationLevel) {
        if (educationLevel == null || educationLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("Education level cannot be empty!");
        }
        this.educationLevel = educationLevel.trim();
    }

    // Getters
    public String getEmployeeId() {
        return employeeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public int getProcessedLoans() {
        return processedLoans;
    }

    public void incrementProcessedLoans() {
        processedLoans++;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + employeeId + ") - Phone: " + phoneNumber +
                " - Address: " + address + " - Education: " + educationLevel +
                " - Processed loans: " + processedLoans + " - Username: " + username;
    }
}