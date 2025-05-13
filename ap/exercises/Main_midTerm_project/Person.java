package ap.exercises.Main_midTerm_project;

public class Person {
    private String firstName;
    private String lastName;
    private String password;

    public Person(String firstName, String lastName, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null && password.length() == 8) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must be exactly 8 characters!");
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
