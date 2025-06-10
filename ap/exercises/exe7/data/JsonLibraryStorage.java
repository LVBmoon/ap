package ap.exercises.exe7.data;

import ap.exercises.midTerm_project.version2_part2.core.Borrow;
import ap.exercises.midTerm_project.version2_part2.core.BorrowRequest;
import ap.exercises.midTerm_project.version2_part2.core.Library;
import ap.exercises.midTerm_project.version2_part2.model.Book;
import ap.exercises.midTerm_project.version2_part2.model.Librarian;
import ap.exercises.midTerm_project.version2_part2.model.LibraryManager;
import ap.exercises.midTerm_project.version2_part2.model.Student;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
        import java.time.LocalDate;

public abstract class JsonLibraryStorage extends LibraryStorage {
    private static final String JSON_BOOKS_FILE = "books.json";
    private static final String JSON_STUDENTS_FILE = "students.json";
    private static final String JSON_LIBRARIANS_FILE = "librarians.json";
    private static final String JSON_MANAGER_FILE = "manager.json";
    private static final String JSON_BORROWS_FILE = "borrows.json";
    private static final String JSON_REQUESTS_FILE = "requests.json";
    private final Gson gson;

    public JsonLibraryStorage(String fileAddress) {
        super(fileAddress);
        this.gson = new Gson();
    }

    @Override
    public void save(Library library) throws IOException {
        // Save books
        try (PrintWriter writer = new PrintWriter(new FileWriter(JSON_BOOKS_FILE))) {
            JsonArray booksArray = new JsonArray();
            for (int i = 0; i < library.getBooks().size(); i++) {
                Book book = library.getBooks().get(i);
                JsonObject bookJson = new JsonObject();
                bookJson.addProperty("title", book.getTitle());
                bookJson.addProperty("author", book.getAuthor());
                bookJson.addProperty("publicationYear", book.getPublicationYear());
                bookJson.addProperty("pageCount", book.getPageCount());
                bookJson.addProperty("isAvailable", book.isAvailable());
                bookJson.addProperty("borrowCount", book.getBorrowCount());
                booksArray.add(bookJson);
            }
            writer.println(gson.toJson(booksArray));
        } catch (IOException e) {
            System.err.println("Error saving books to JSON: " + e.getMessage());
        }

        // Save students
        try (PrintWriter writer = new PrintWriter(new FileWriter(JSON_STUDENTS_FILE))) {
            JsonArray studentsArray = new JsonArray();
            for (int i = 0; i < library.getStudents().size(); i++) {
                Student student = library.getStudents().get(i);
                JsonObject studentJson = new JsonObject();
                studentJson.addProperty("firstName", student.getFirstName());
                studentJson.addProperty("lastName", student.getLastName());
                studentJson.addProperty("studentId", student.getStudentId());
                studentJson.addProperty("fieldOfStudy", student.getFieldOfStudy());
                studentJson.addProperty("joinDate", student.getJoinDate().toString());
                studentJson.addProperty("username", student.getUsername());
                studentJson.addProperty("password", student.getPassword());
                JsonArray borrowedBooks = new JsonArray();
                for (int j = 0; j < student.getBorrowedBooks().size(); j++) {
                    borrowedBooks.add(student.getBorrowedBooks().get(j).getTitle());
                }
                studentJson.add("borrowedBooks", borrowedBooks);
                studentsArray.add(studentJson);
            }
            writer.println(gson.toJson(studentsArray));
        } catch (IOException e) {
            System.err.println("Error saving students to JSON: " + e.getMessage());
        }

        // Save librarians
        try (PrintWriter writer = new PrintWriter(new FileWriter(JSON_LIBRARIANS_FILE))) {
            JsonArray librariansArray = new JsonArray();
            for (int i = 0; i < library.getLibrarians().size(); i++) {
                Librarian librarian = library.getLibrarians().get(i);
                JsonObject librarianJson = new JsonObject();
                librarianJson.addProperty("firstName", librarian.getFirstName());
                librarianJson.addProperty("lastName", librarian.getLastName());
                librarianJson.addProperty("employeeId", librarian.getEmployeeId());
                librarianJson.addProperty("phoneNumber", librarian.getPhoneNumber());
                librarianJson.addProperty("address", librarian.getAddress());
                librarianJson.addProperty("processedLoans", librarian.getProcessedLoans());
                librarianJson.addProperty("username", librarian.getUsername());
                librarianJson.addProperty("password", librarian.getPassword());
                librarianJson.addProperty("educationLevel", librarian.getEducationLevel());
                librariansArray.add(librarianJson);
            }
            writer.println(gson.toJson(librariansArray));
        } catch (IOException e) {
            System.err.println("Error saving librarians to JSON: " + e.getMessage());
        }

        // Save manager
        try (PrintWriter writer = new PrintWriter(new FileWriter(JSON_MANAGER_FILE))) {
            JsonObject managerJson = new JsonObject();
            LibraryManager manager = library.getManager();
            managerJson.addProperty("firstName", manager.getFirstName());
            managerJson.addProperty("lastName", manager.getLastName());
            managerJson.addProperty("educationLevel", manager.getEducationLevel());
            managerJson.addProperty("username", manager.getUsername());
            managerJson.addProperty("password", manager.getPassword());
            writer.println(gson.toJson(managerJson));
        } catch (IOException e) {
            System.err.println("Error saving manager to JSON: " + e.getMessage());
        }

        // Save borrows
        try (PrintWriter writer = new PrintWriter(new FileWriter(JSON_BORROWS_FILE))) {
            JsonArray borrowsArray = new JsonArray();
            for (int i = 0; i < library.getBorrows().size(); i++) {
                Borrow borrow = library.getBorrows().get(i);
                JsonObject borrowJson = new JsonObject();
                borrowJson.addProperty("bookTitle", borrow.getBook().getTitle());
                borrowJson.addProperty("studentId", borrow.getStudent().getStudentId());
                borrowJson.addProperty("issuedBy", borrow.getIssuedBy().getEmployeeId());
                borrowJson.addProperty("borrowDate", borrow.getBorrowDate().toString());
                borrowJson.addProperty("dueDate", borrow.getDueDate().toString());
                borrowJson.addProperty("isReturned", borrow.isReturned());
                if (borrow.isReturned()) {
                    borrowJson.addProperty("receivedBy", borrow.getReceivedBy().getEmployeeId());
                    borrowJson.addProperty("returnDate", borrow.getReturnDate().toString());
                }
                borrowsArray.add(borrowJson);
            }
            writer.println(gson.toJson(borrowsArray));
        } catch (IOException e) {
            System.err.println("Error saving borrows to JSON: " + e.getMessage());
        }

        // Save borrow requests
        try (PrintWriter writer = new PrintWriter(new FileWriter(JSON_REQUESTS_FILE))) {
            JsonArray requestsArray = new JsonArray();
            for (int i = 0; i < library.getBorrowRequests().size(); i++) {
                BorrowRequest request = library.getBorrowRequests().get(i);
                JsonObject requestJson = new JsonObject();
                requestJson.addProperty("bookTitle", request.getBook().getTitle());
                requestJson.addProperty("studentId", request.getStudent().getStudentId());
                requestJson.addProperty("assignedLibrarian", request.getAssignedLibrarian().getEmployeeId());
                requestJson.addProperty("requestDate", request.getRequestDate().toString());
                requestJson.addProperty("status", request.getStatus());
                requestJson.addProperty("isReturnRequest", request.isReturnRequest());
                requestsArray.add(requestJson);
            }
            writer.println(gson.toJson(requestsArray));
        } catch (IOException e) {
            System.err.println("Error saving borrow requests to JSON: " + e.getMessage());
        }
    }

    @Override
    public Library load() throws IOException {
        LibraryManager manager = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(JSON_MANAGER_FILE))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            JsonObject managerJson = JsonParser.parseString(json.toString()).getAsJsonObject();
            manager = new LibraryManager(
                    managerJson.get("firstName").getAsString(),
                    managerJson.get("lastName").getAsString(),
                    managerJson.get("educationLevel").getAsString(),
                    managerJson.get("username").getAsString(),
                    managerJson.get("password").getAsString()
            );
        } catch (IOException e) {
            System.err.println("Error loading manager from JSON: " + e.getMessage());
        }
        if (manager == null) {
            manager = new LibraryManager("Admin", "Manager", "PhD", "admin", "admin123");
        }

        Library library = new Library("University Central Library", manager);

        // Load books
        try (BufferedReader reader = new BufferedReader(new FileReader(JSON_BOOKS_FILE))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            JsonArray booksArray = JsonParser.parseString(json.toString()).getAsJsonArray();
            for (int i = 0; i < booksArray.size(); i++) {
                JsonObject bookJson = booksArray.get(i).getAsJsonObject();
                try {
                    Book book = new Book(
                            bookJson.get("title").getAsString(),
                            bookJson.get("author").getAsString(),
                            bookJson.get("publicationYear").getAsInt(),
                            bookJson.get("pageCount").getAsInt()
                    );
                    book.setAvailable(bookJson.get("isAvailable").getAsBoolean());
                    for (int j = 0; j < bookJson.get("borrowCount").getAsInt(); j++) {
                        book.incrementBorrowCount();
                    }
                    library.addBook(book);
                } catch (Exception e) {
                    System.err.println("Error loading book from JSON: " + bookJson + ", Reason: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading books from JSON: " + e.getMessage());
        }

        // Load librarians
        try (BufferedReader reader = new BufferedReader(new FileReader(JSON_LIBRARIANS_FILE))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            JsonArray librariansArray = JsonParser.parseString(json.toString()).getAsJsonArray();
            for (int i = 0; i < librariansArray.size(); i++) {
                JsonObject librarianJson = librariansArray.get(i).getAsJsonObject();
                try {
                    Librarian librarian = new Librarian(
                            librarianJson.get("firstName").getAsString(),
                            librarianJson.get("lastName").getAsString(),
                            librarianJson.get("employeeId").getAsString(),
                            librarianJson.get("username").getAsString(),
                            librarianJson.get("password").getAsString(),
                            librarianJson.get("educationLevel").getAsString()
                    );
                    librarian.setPhoneNumber(librarianJson.get("phoneNumber").getAsString());
                    librarian.setAddress(librarianJson.get("address").getAsString());
                    for (int j = 0; j < librarianJson.get("processedLoans").getAsInt(); j++) {
                        librarian.incrementProcessedLoans();
                    }
                    library.addLibrarian(librarian);
                } catch (Exception e) {
                    System.err.println("Error loading librarian from JSON: " + librarianJson + ", Reason: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading librarians from JSON: " + e.getMessage());
        }

        // Load students
        int studentCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(JSON_STUDENTS_FILE))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            JsonArray studentsArray = JsonParser.parseString(json.toString()).getAsJsonArray();
            for (int i = 0; i < studentsArray.size(); i++) {
                JsonObject studentJson = studentsArray.get(i).getAsJsonObject();
                try {
                    Student student = new Student(
                            studentJson.get("firstName").getAsString(),
                            studentJson.get("lastName").getAsString(),
                            studentJson.get("studentId").getAsString(),
                            studentJson.get("fieldOfStudy").getAsString(),
                            studentJson.get("username").getAsString(),
                            studentJson.get("password").getAsString(),
                            library
                    );
                    JsonArray borrowedBooks = studentJson.get("borrowedBooks").getAsJsonArray();
                    for (int j = 0; j < borrowedBooks.size(); j++) {
                        Book book = library.findBookByTitle(borrowedBooks.get(j).getAsString());
                        if (book != null) {
                            student.borrowBook(book);
                        } else {
                            System.err.println("Book not found for title: " + borrowedBooks.get(j).getAsString());
                        }
                    }
                    library.addStudent(student);
                    studentCount++;
                } catch (Exception e) {
                    System.err.println("Error loading student from JSON: " + studentJson + ", Reason: " + e.getMessage());
                }
            }
            System.out.println("Loaded " + studentCount + " student(s) successfully.");
        } catch (IOException e) {
            System.err.println("Error loading students from JSON: " + e.getMessage());
        }

        // Load borrows
        try (BufferedReader reader = new BufferedReader(new FileReader(JSON_BORROWS_FILE))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            JsonArray borrowsArray = JsonParser.parseString(json.toString()).getAsJsonArray();
            for (int i = 0; i < borrowsArray.size(); i++) {
                JsonObject borrowJson = borrowsArray.get(i).getAsJsonObject();
                try {
                    Book book = library.findBookByTitle(borrowJson.get("bookTitle").getAsString());
                    Student student = library.findStudentByStudentId(borrowJson.get("studentId").getAsString());
                    Librarian issuedBy = library.findLibrarianByEmployeeId(borrowJson.get("issuedBy").getAsString());
                    if (book != null && student != null && issuedBy != null) {
                        Borrow borrow = new Borrow(book, student, issuedBy, LocalDate.parse(borrowJson.get("borrowDate").getAsString()));
                        if (borrowJson.get("isReturned").getAsBoolean()) {
                            Librarian receivedBy = library.findLibrarianByEmployeeId(borrowJson.get("receivedBy").getAsString());
                            if (receivedBy != null) {
                                borrow.returnBook(receivedBy, LocalDate.parse(borrowJson.get("returnDate").getAsString()));
                            }
                        }
                        library.addBorrow(borrow);
                    }
                } catch (Exception e) {
                    System.err.println("Error loading borrow from JSON: " + borrowJson + ", Reason: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading borrows from JSON: " + e.getMessage());
        }

        // Load borrow requests
        try (BufferedReader reader = new BufferedReader(new FileReader(JSON_REQUESTS_FILE))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            JsonArray requestsArray = JsonParser.parseString(json.toString()).getAsJsonArray();
            for (int i = 0; i < requestsArray.size(); i++) {
                JsonObject requestJson = requestsArray.get(i).getAsJsonObject();
                try {
                    Book book = library.findBookByTitle(requestJson.get("bookTitle").getAsString());
                    Student student = library.findStudentByStudentId(requestJson.get("studentId").getAsString());
                    Librarian assignedLibrarian = library.findLibrarianByEmployeeId(requestJson.get("assignedLibrarian").getAsString());
                    if (book != null && student != null && assignedLibrarian != null) {
                        BorrowRequest request = new BorrowRequest(book, student, assignedLibrarian,
                                LocalDate.parse(requestJson.get("requestDate").getAsString()), requestJson.get("isReturnRequest").getAsBoolean());
                        request.setStatus(requestJson.get("status").getAsString());
                        library.addBorrowRequest(request);
                    }
                } catch (Exception e) {
                    System.err.println("Error loading borrow request from JSON: " + requestJson + ", Reason: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading borrow requests from JSON: " + e.getMessage());
        }

        return library;
    }
}