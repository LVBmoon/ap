package ap.exercises.Main_midTerm_project;

public class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String studentFirstName) {
        if (studentFirstName != null && studentFirstName.length() > 2) {
            this.firstName = studentFirstName;
        }
        else {
            throw new IllegalArgumentException("First Name cannot be empty or less than 3 characters!");
        }
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String studentLastName) {
        if (studentLastName != null && studentLastName.length() > 2) {
            this.lastName = studentLastName;
        }
        else {
            throw new IllegalArgumentException("Last Name cannot be empty or less than 3 characters!");
        }
    }

    @Override
    public String toString() {
        return "First Name : " + firstName + " | Last Name : " + lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return firstName.equals(person.firstName) && lastName.equals(person.lastName);
    }
}
