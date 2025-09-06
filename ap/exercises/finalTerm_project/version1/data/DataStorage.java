package ap.exercises.finalTerm_project.version1.data;

import java.time.format.DateTimeParseException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.io.*;

import ap.exercises.finalTerm_project.version1.model.LibraryManager;
import ap.exercises.finalTerm_project.version1.core.Borrow;
import ap.exercises.finalTerm_project.version1.core.BorrowRequest;
import ap.exercises.finalTerm_project.version1.core.Library;
import ap.exercises.finalTerm_project.version1.model.Book;
import ap.exercises.finalTerm_project.version1.model.Librarian;
import ap.exercises.finalTerm_project.version1.model.Student;


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
        try {
            return URLEncoder.encode(field, StandardCharsets.UTF_8.toString()).replace(",", "%2C").replace("|", "%7C");
        } catch (UnsupportedEncodingException e) {
            return field.replace(",", "_").replace("|", "_");
        }
    }

    private String unescapeField(String field) {
        if (field == null || field.isEmpty()) {
            return "";
        }
        try {
            return URLDecoder.decode(field, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            return field.replace("_", " ");
        }
    }

    public void saveLibrary(Library library) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : library.getBooks()) {
                writer.println(escapeField(book.getBookId()) + "," + escapeField(book.getTitle()) + "," + escapeField(book.getAuthor()) + "," +
                        book.getPublicationYear() + "," + book.getPageCount() + "," +
                        book.isAvailable() + "," + book.getBorrowCount());
            }
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student student : library.getStudents()) {
                String borrowedBookIds = "";
                if (!student.getBorrowedBooks().isEmpty()) {
                    for (int j = 0; j < student.getBorrowedBooks().size(); j++) {
                        borrowedBookIds += escapeField(student.getBorrowedBooks().get(j).getBookId());
                        if (j < student.getBorrowedBooks().size() - 1) {
                            borrowedBookIds += "|";
                        }
                    }
                }
                writer.println(escapeField(student.getFirstName()) + "," +
                        escapeField(student.getLastName()) + "," +
                        escapeField(student.getStudentId()) + "," +
                        escapeField(student.getFieldOfStudy()) + "," +
                        student.getJoinDate() + "," +
                        escapeField(student.getUsername()) + "," +
                        escapeField(student.getPassword()) + "," +
                        borrowedBookIds + "," +
                        student.isActive());
            }
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(LIBRARIANS_FILE))) {
            for (Librarian librarian : library.getLibrarians()) {
                writer.println(escapeField(librarian.getFirstName()) + "," + escapeField(librarian.getLastName()) + "," +
                        escapeField(librarian.getEmployeeId()) + "," + escapeField(librarian.getPhoneNumber()) + "," +
                        escapeField(librarian.getAddress()) + "," + librarian.getProcessedLoans() + "," +
                        librarian.getRegisteredBooks() + "," + librarian.getBorrowsGiven() + "," +
                        librarian.getBorrowsReceived() + "," +
                        escapeField(librarian.getUsername()) + "," + escapeField(librarian.getPassword()) + "," +
                        escapeField(librarian.getEducationLevel()));
            }
        } catch (IOException e) {
            System.err.println("Error saving librarians: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(MANAGER_FILE))) {
            LibraryManager manager = library.getManager();
            writer.println(escapeField(manager.getFirstName()) + "," + escapeField(manager.getLastName()) + "," +
                    escapeField(manager.getEducationLevel()) + "," + escapeField(manager.getUsername()) + "," +
                    escapeField(manager.getPassword()));
        } catch (IOException e) {
            System.err.println("Error saving manager: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(BORROWS_FILE))) {
            for (Borrow borrow : library.getBorrows()) {
                String returnInfo = borrow.isReturned() ? "," + escapeField(borrow.getReceivedBy().getEmployeeId()) + "," + borrow.getReturnDate() : ",,";
                writer.println(escapeField(borrow.getBook().getBookId()) + "," + escapeField(borrow.getStudent().getStudentId()) + "," +
                        escapeField(borrow.getIssuedBy().getEmployeeId()) + "," + borrow.getBorrowDate() + "," +
                        borrow.getDueDate() + "," + borrow.isReturned() + returnInfo);
            }
        } catch (IOException e) {
            System.err.println("Error saving borrows: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(REQUESTS_FILE))) {
            for (BorrowRequest request : library.getBorrowRequests()) {
                writer.println(escapeField(request.getBook().getBookId()) + "," +
                        escapeField(request.getStudent().getStudentId()) + "," +
                        escapeField(request.getAssignedLibrarian().getEmployeeId()) + "," +
                        request.getRequestDate() + "," + request.getDueDate() + "," +
                        request.getStatus() + "," + request.isReturnRequest());
            }
        } catch (IOException e) {
            System.err.println("Error saving borrow requests: " + e.getMessage());
        }
    }

    public Library loadLibrary() {
        LibraryManager manager = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(MANAGER_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    manager = new LibraryManager(unescapeField(parts[0]), unescapeField(parts[1]),
                            unescapeField(parts[2]), unescapeField(parts[3]), unescapeField(parts[4]));
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.err.println("Error loading manager: " + e.getMessage());
        }
        if (manager == null) {
            manager = new LibraryManager("Admin", "Manager", "PhD", "admin", "admin123");
        }

        Library library = new Library("University Central Library", manager);

        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    try {
                        Book book = new Book(unescapeField(parts[0]), unescapeField(parts[1]), unescapeField(parts[2]),
                                Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                        book.setAvailable(Boolean.parseBoolean(parts[5]));
                        for (int i = 0; i < Integer.parseInt(parts[6]); i++) {
                            book.incrementBorrowCount();
                        }
                        library.addBook(book);
                    } catch (Exception e) {
                        System.err.println("Error loading book from line: " + line + ", Reason: " + e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.err.println("Error loading books: " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(LIBRARIANS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 12) {
                    try {
                        if (library.findLibrarianByEmployeeId(parts[2]) != null) {
                            continue;
                        }
                        Librarian librarian = new Librarian(unescapeField(parts[0]), unescapeField(parts[1]),
                                parts[2], unescapeField(parts[9]), unescapeField(parts[10]), unescapeField(parts[11]));
                        String phoneNumber = parts[3].isEmpty() ? "" : unescapeField(parts[3]);
                        librarian.setPhoneNumber(phoneNumber);
                        librarian.setAddress(unescapeField(parts[4]));
                        for (int i = 0; i < Integer.parseInt(parts[5]); i++) {
                            librarian.incrementProcessedLoans();
                        }
                        for (int i = 0; i < Integer.parseInt(parts[6]); i++) {
                            librarian.incrementRegisteredBooks();
                        }
                        for (int i = 0; i < Integer.parseInt(parts[7]); i++) {
                            librarian.incrementBorrowsGiven();
                        }
                        for (int i = 0; i < Integer.parseInt(parts[8]); i++) {
                            librarian.incrementBorrowsReceived();
                        }
                        library.addLibrarian(librarian);
                    } catch (Exception e) {
                        System.err.println("Error loading librarian from line: " + line + ", Reason: " + e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.err.println("Error loading librarians: " + e.getMessage());
        }

        int studentCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 8) {
                    System.err.println("Invalid student line format (not enough fields): " + line);
                    continue;
                }
                try {
                    LocalDate joinDate;
                    try {
                        joinDate = LocalDate.parse(parts[4]);
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException("Invalid join date format: " + parts[4]);
                    }
                    Student student = new Student(
                            unescapeField(parts[0]),
                            unescapeField(parts[1]),
                            parts[2],
                            unescapeField(parts[3]),
                            unescapeField(parts[5]),
                            unescapeField(parts[6]),
                            library
                    );
                    student.setJoinDate(joinDate);
                    student.setActive(parts.length > 8 ? Boolean.parseBoolean(parts[8]) : true);
                    if (parts.length > 7 && !parts[7].isEmpty()) {
                        String[] borrowedBookIds = parts[7].split("\\|");
                        for (String bookId : borrowedBookIds) {
                            Book book = library.findBookByBookId(unescapeField(bookId));
                            if (book != null) {
                                student.addBorrowedBookFromStorage(book);
                            } else {
                                System.err.println("Book not found for ID: " + unescapeField(bookId));
                            }
                        }
                    }
                    library.addStudent(student);
                    studentCount++;
                } catch (IllegalArgumentException e) {
                    System.err.println("Validation error loading student from line: " + line);
                    System.err.println("Reason: " + e.getMessage());
                    System.out.println("Skipping invalid student data: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Unexpected error loading student from line: " + line);
                    System.err.println("Reason: " + e.getMessage());
                    System.out.println("Skipping student data: " + e.getMessage());
                }
            }
            System.out.println("Loaded " + studentCount + " student(s) successfully.");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(BORROWS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    try {
                        Book book = library.findBookByBookId(unescapeField(parts[0]));
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
                    } catch (Exception e) {
                        System.err.println("Error loading borrow from line: " + line + ", Reason: " + e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.err.println("Error loading borrows: " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(REQUESTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    try {
                        Book book = library.findBookByBookId(unescapeField(parts[0]));
                        Student student = library.findStudentByStudentId(parts[1]);
                        Librarian assignedLibrarian = library.findLibrarianByEmployeeId(parts[2]);
                        if (book != null && student != null && assignedLibrarian != null) {
                            BorrowRequest request = new BorrowRequest(book, student, assignedLibrarian,
                                    LocalDate.parse(parts[3]), LocalDate.parse(parts[4]), Boolean.parseBoolean(parts[6]));
                            request.setStatus(BorrowRequest.RequestStatus.valueOf(parts[5]));
                            library.addBorrowRequest(request);
                        }
                    } catch (Exception e) {
                        System.err.println("Error loading borrow request from line: " + line + ", Reason: " + e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.err.println("Error loading borrow requests: " + e.getMessage());
        }

        return library;
    }
}