package ap.exercises.Main_midTerm_project;

public class Librarian extends Person {
    private int librarianID;
//    private LocalDate hireDate;
//    private int cntOfGettingAndGivingBooks;
//    private int salary;
//    private String liberianEducation;
    public Librarian(String firstName, String lastName, int librarianID) {
        super(firstName, lastName);
        setLibrarianID(librarianID);
    }

    public int getLibrarianID() {
        return librarianID;
    }
    public void setLibrarianID(int librarianID) {
        if (String.valueOf(this.librarianID).length() == 9) {
            this.librarianID = this.librarianID;
        }
        else {
            throw new IllegalArgumentException("The librarian ID cannot have less than 9 digits");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Librarian ID : " + librarianID;
    }
}
