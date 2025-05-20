package ap.exercises.midTerm_project.version2_part2;

import java.io.*;
import java.time.LocalDate;

public class DataStorage {
    private static final String BOOKS_FILE = "books.txt";
    private static final String STUDENTS_FILE = "students.txt";
    private static final String LIBRARIANS_FILE = "librarians.txt";
    private static final String MANAGER_FILE = "manager.txt";
    private static final String BORROWS_FILE = "borrows.txt";
    private static final String REQUESTS_FILE = "requests.txt";

    private String escapeField(String field) {
        if (field == null) {
            return "";
        }
        return field.replace(",", "_").replace("|", "_");
    }

    public void saveLibrary(Library library) {
        // Save books
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (int i = 0; i < library.getBooks().size(); i++) {
                Book book = library.getBooks().get(i);
                writer.println(escapeField(book.getTitle()) + "," + escapeField(book.getAuthor()) + "," +
                        book.getPublicationYear() + "," + book.getPageCount() + "," +
                        book.isAvailable() + "," + book.getBorrowCount());
            }
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }

        // Save students
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENTS_FILE))) {
            for (int i = 0; i < library.getStudents().size(); i++) {
                Student student = library.getStudents().get(i);
                String borrowedTitles = "";
                for (int j = 0; j < student.getBorrowedBooks().size(); j++) {
                    borrowedTitles += escapeField(student.getBorrowedBooks().get(j).getTitle());
                    if (j < student.getBorrowedBooks().size() - 1) {
                        borrowedTitles += "|";
                    }
                }
                writer.println(escapeField(student.getFirstName()) + "," + escapeField(student.getLastName()) + "," +
                        escapeField(student.getStudentId()) + "," + escapeField(student.getFieldOfStudy()) + "," +
                        student.getJoinDate() + "," + escapeField(student.getUsername()) + "," +
                        escapeField(student.getPassword()) + "," + borrowedTitles);
            }
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }

        // Save librarians
        try (PrintWriter writer = new PrintWriter(new FileWriter(LIBRARIANS_FILE))) {
            for (int i = 0; i < library.getLibrarians().size(); i++) {
                Librarian librarian = library.getLibrarians().get(i);
                writer.println(escapeField(librarian.getFirstName()) + "," + escapeField(librarian.getLastName()) + "," +
                        escapeField(librarian.getEmployeeId()) + "," + escapeField(librarian.getPhoneNumber()) + "," +
                        escapeField(librarian.getAddress()) + "," + librarian.getProcessedLoans() + "," +
                        escapeField(librarian.getUsername()) + "," + escapeField(librarian.getPassword()) + "," +
                        escapeField(librarian.getEducationLevel()));
            }
        } catch (IOException e) {
            System.err.println("Error saving librarians: " + e.getMessage());
        }

        // Save manager
        try (PrintWriter writer = new PrintWriter(new FileWriter(MANAGER_FILE))) {
            LibraryManager manager = library.getManager();
            writer.println(escapeField(manager.getFirstName()) + "," + escapeField(manager.getLastName()) + "," +
                    escapeField(manager.getEducationLevel()) + "," + escapeField(manager.getUsername()) + "," +
                    escapeField(manager.getPassword()));
        } catch (IOException e) {
            System.err.println("Error saving manager: " + e.getMessage());
        }

        // Save borrows
        try (PrintWriter writer = new PrintWriter(new FileWriter(BORROWS_FILE))) {
            for (int i = 0; i < library.getBorrows().size(); i++) {
                Borrow borrow = library.getBorrows().get(i);
                String returnInfo = borrow.isReturned() ? "," + escapeField(borrow.getReceivedBy().getEmployeeId()) + "," + borrow.getReturnDate() : ",,";
                writer.println(escapeField(borrow.getBook().getTitle()) + "," + escapeField(borrow.getStudent().getStudentId()) + "," +
                        escapeField(borrow.getIssuedBy().getEmployeeId()) + "," + borrow.getBorrowDate() + "," +
                        borrow.getDueDate() + "," + borrow.isReturned() + returnInfo);
            }
        } catch (IOException e) {
            System.err.println("Error saving borrows: " + e.getMessage());
        }

        // Save borrow requests
        try (PrintWriter writer = new PrintWriter(new FileWriter(REQUESTS_FILE))) {
            for (BorrowRequest request : library.getBorrowRequests()) {
                writer.println(escapeField(request.getBook().getTitle()) + "," +
                        escapeField(request.getStudent().getStudentId()) + "," +
                        escapeField(request.getAssignedLibrarian().getEmployeeId()) + "," +
                        request.getRequestDate() + "," + request.getStatus() + "," +
                        request.isReturnRequest());
            }
        } catch (IOException e) {
            System.err.println("Error saving borrow requests: " + e.getMessage());
        }
    }

    public Library loadLibrary() {
        LibraryManager manager = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(MANAGER_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    manager = new LibraryManager(parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading manager: " + e.getMessage());
        }
        if (manager == null) {
            manager = new LibraryManager("Admin", "Manager", "PhD", "admin", "admin123");
        }

        Library library = new Library("University Central Library", manager);

        // Load books
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    Book book = new Book(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                    book.setAvailable(Boolean.parseBoolean(parts[4]));
                    for (int i = 0; i < Integer.parseInt(parts[5]); i++) {
                        book.incrementBorrowCount();
                    }
                    library.addBook(book);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading books: " + e.getMessage());
        }

        // Load librarians
        try (BufferedReader reader = new BufferedReader(new FileReader(LIBRARIANS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 9) {
                    Librarian librarian = new Librarian(parts[0], parts[1], parts[2], parts[6], parts[7], parts[8]);
                    String phoneNumber = parts[3].isEmpty() || parts[3].equals("_") ? "" : parts[3];
                    librarian.setPhoneNumber(phoneNumber);
                    librarian.setAddress(parts[4]);
                    for (int i = 0; i < Integer.parseInt(parts[5]); i++) {
                        librarian.incrementProcessedLoans();
                    }
                    library.addLibrarian(librarian);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading librarians: " + e.getMessage());
        }

        // Load students
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    Student student = new Student(parts[0], parts[1], parts[2], parts[3], parts[5], parts[6]);
                    if (!parts[7].isEmpty()) {
                        String[] borrowedTitles = parts[7].split("\\|");
                        for (int i = 0; i < borrowedTitles.length; i++) {
                            Book book = library.findBookByTitle(borrowedTitles[i]);
                            if (book != null) {
                                student.borrowBook(book);
                            }
                        }
                    }
                    library.addStudent(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }

        // Load borrows
        try (BufferedReader reader = new BufferedReader(new FileReader(BORROWS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    Book book = library.findBookByTitle(parts[0]); // Fixed
                    Student student = library.findStudentByStudentId(parts[1]);
                    Librarian issuedBy = library.findLibrarianByEmployeeId(parts[2]);
                    if (book != null && student != null && issuedBy != null) {
                        Borrow borrow = new Borrow(book, student, issuedBy, LocalDate.parse(parts[3]));
                        if (Boolean.parseBoolean(parts[5]) && parts.length >= 8) {
                            Librarian receivedBy = library.findLibrarianByEmployeeId(parts[6]);
                            if (receivedBy != null) {
                                borrow.returnBook(receivedBy, LocalDate.parse(parts[7]));
                            }
                        }
                        library.addBorrow(borrow);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading borrows: " + e.getMessage());
        }

        // Load borrow requests
        try (BufferedReader reader = new BufferedReader(new FileReader(REQUESTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    Book book = library.findBookByTitle(parts[0]);
                    Student student = library.findStudentByStudentId(parts[1]);
                    Librarian assignedLibrarian = library.findLibrarianByEmployeeId(parts[2]);
                    if (book != null && student != null && assignedLibrarian != null) {
                        BorrowRequest request = new BorrowRequest(book, student, assignedLibrarian,
                                LocalDate.parse(parts[3]),
                                Boolean.parseBoolean(parts[5]));
                        request.setStatus(parts[4]); // Use setter
                        library.addBorrowRequest(request);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading borrow requests: " + e.getMessage());
        }

        return library;
    }
}