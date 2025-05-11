package ap.exercises.Main_midTerm_project;

public class LibraryManager extends Person{
    private String managerEducation;

    public LibraryManager(String firstName, String lastName, String managerEducation) {
        super(firstName,lastName);
        setManagerEducation(managerEducation);
    }

    public String getManagerEducation() {
        return managerEducation;
    }
    public void setManagerEducation(String managerEducation) {
        if (managerEducation != null && !managerEducation.isEmpty()) {
            this.managerEducation = managerEducation;
        }
        else {
            throw new IllegalArgumentException("The manager education cannot be empty!");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Education : " + managerEducation;
    }
}
