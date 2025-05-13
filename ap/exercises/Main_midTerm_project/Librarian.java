package ap.exercises.Main_midTerm_project;

import javax.swing.*;

public class Librarian extends Person {
    private int librarianID;
//    private LocalDate hireDate;
//    private int cntOfGettingAndGivingBooks;
//    private int salary;
//    private String liberianEducation;
    public Librarian(String firstName, String lastName, int librarianID, String password) {
        super(firstName, lastName,password);
        setLibrarianID(librarianID);
    }

    public int getLibrarianID() {
        return librarianID;
    }
    public void setLibrarianID(int librarianID) {
        if (String.valueOf(librarianID).length() == 9) {
            this.librarianID = librarianID;
        }
        else {
            throw new IllegalArgumentException("The librarian ID cannot have less than 9 digits");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Librarian ID : " + librarianID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Librarian librarian = (Librarian) obj;
        return librarianID == librarian.librarianID;
    }
}
