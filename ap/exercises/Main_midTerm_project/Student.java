package ap.exercises.Main_midTerm_project;

import java.time.LocalDate;

public class Student extends Person {
    private String studentMajor;
    private int studentID;
    private final LocalDate membershipDate;

    public Student (String studentFirstName, String studentLastName, String studentMajor, int studentID,
                    LocalDate membershipDate, String password) {
        super(studentFirstName,studentLastName,password);
        setStudentID(studentID);
        this.membershipDate = membershipDate;
    }


    public String getStudentMajor() {
        return studentMajor;
    }
    public void setStudentMajor(String studentMajor) {
        if (studentMajor != null && studentMajor.length() > 2) {
            this.studentMajor = studentMajor;
        }
        else {
            throw new IllegalArgumentException("Major cannot be empty or less than 3 characters!");
        }
    }

    public int getStudentID() {
        return studentID;
    }
    public void setStudentID(int studentID) {
        if (String.valueOf(studentID).length() == 9){
            this.studentID = studentID;
        }
        else {
            throw new IllegalArgumentException("Student ID must be a 9-digit number!");
        }
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    @Override
    public String toString() {
        return super.toString() + " | ID :" + studentID + " | Major : " + studentMajor +
                " | Membership Date : " + membershipDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Student student = (Student) obj;

        if (!super.equals(obj)) {return false;}
        return studentID == student.studentID;
    }
}
