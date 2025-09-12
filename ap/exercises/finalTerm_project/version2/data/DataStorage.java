package ap.exercises.finalTerm_project.version2.data;
//---
import ap.exercises.finalTerm_project.version2.core.Borrow;
import ap.exercises.finalTerm_project.version2.core.BorrowRequest;
import ap.exercises.finalTerm_project.version2.core.Library;
import ap.exercises.finalTerm_project.version2.model.Book;
import ap.exercises.finalTerm_project.version2.model.Librarian;
import ap.exercises.finalTerm_project.version2.model.LibraryManager;
import ap.exercises.finalTerm_project.version2.model.Student;
//---
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
//---
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private static final String BOOKS_FILE = "books.json";
    private static final String STUDENTS_FILE = "students.json";
    private static final String LIBRARIANS_FILE = "librarians.json";
    private static final String MANAGER_FILE = "manager.json";
    private static final String BORROWS_FILE = "borrows.json";
    private static final String REQUESTS_FILE = "requests.json";

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void saveLibrary(Library library) {
        try (Writer writer = new FileWriter(BOOKS_FILE)) {
            gson.toJson(library.getBooks(), writer);
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }

        try (Writer writer = new FileWriter(STUDENTS_FILE)) {
            gson.toJson(library.getStudents(), writer);
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }

        try (Writer writer = new FileWriter(LIBRARIANS_FILE)) {
            gson.toJson(library.getLibrarians(), writer);
        } catch (IOException e) {
            System.err.println("Error saving librarians: " + e.getMessage());
        }

        try (Writer writer = new FileWriter(MANAGER_FILE)) {
            List<LibraryManager> managers = new ArrayList<>();
            managers.add(library.getManager());
            gson.toJson(managers, writer);
        } catch (IOException e) {
            System.err.println("Error saving manager: " + e.getMessage());
        }

        try (Writer writer = new FileWriter(BORROWS_FILE)) {
            gson.toJson(library.getBorrows(), writer);
        } catch (IOException e) {
            System.err.println("Error saving borrows: " + e.getMessage());
        }

        try (Writer writer = new FileWriter(REQUESTS_FILE)) {
            gson.toJson(library.getBorrowRequests(), writer);
        } catch (IOException e) {
            System.err.println("Error saving borrow requests: " + e.getMessage());
        }
    }

    public Library loadLibrary() {
        LibraryManager manager = loadManager();
        if (manager == null) {
            manager = new LibraryManager("Admin", "Manager", "PhD", "admin", "admin123");
        }

        Library library = new Library("University Central Library", manager);
        loadBooks(library);
        loadLibrarians(library);
        loadStudents(library);
        loadBorrows(library);
        loadRequests(library);

        return library;
    }

    private LibraryManager loadManager() {
        try (Reader reader = new FileReader(MANAGER_FILE)) {
            Type listType = new TypeToken<List<LibraryManager>>(){}.getType();
            List<LibraryManager> managers = gson.fromJson(reader, listType);
            return managers != null && !managers.isEmpty() ? managers.get(0) : null;
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error loading manager: " + e.getMessage());
            return null;
        }
    }

    private void loadBooks(Library library) {
        try (Reader reader = new FileReader(BOOKS_FILE)) {
            Type listType = new TypeToken<List<Book>>(){}.getType();
            List<Book> loadedBooks = gson.fromJson(reader, listType);
            if (loadedBooks != null) {
                for (Book book : loadedBooks) {
                    library.addBook(book);
                }
            }
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error loading books: " + e.getMessage());
        }
    }

    private void loadLibrarians(Library library) {
        try (Reader reader = new FileReader(LIBRARIANS_FILE)) {
            Type listType = new TypeToken<List<Librarian>>(){}.getType();
            List<Librarian> loadedLibrarians = gson.fromJson(reader, listType);
            if (loadedLibrarians != null) {
                for (Librarian librarian : loadedLibrarians) {
                    if (library.findLibrarianByEmployeeId(librarian.getEmployeeId()) == null) {
                        library.addLibrarian(librarian);
                    }
                }
            }
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error loading librarians: " + e.getMessage());
        }
    }

    private void loadStudents(Library library) {
        int studentCount = 0;
        try (Reader reader = new FileReader(STUDENTS_FILE)) {
            Type listType = new TypeToken<List<Student>>(){}.getType();
            List<Student> loadedStudents = gson.fromJson(reader, listType);
            if (loadedStudents != null) {
                for (Student student : loadedStudents) {
                    try {
                        library.addStudent(student);
                        studentCount++;
                    } catch (Exception e) {
                        System.err.println("Error loading student: " + e.getMessage());
                    }
                }
            }
            System.out.println("Loaded " + studentCount + " student(s) successfully.");
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
    }

    private void loadBorrows(Library library) {
        try (Reader reader = new FileReader(BORROWS_FILE)) {
            Type listType = new TypeToken<List<Borrow>>(){}.getType();
            List<Borrow> loadedBorrows = gson.fromJson(reader, listType);
            if (loadedBorrows != null) {
                for (Borrow borrow : loadedBorrows) {
                    library.addBorrow(borrow);
                }
            }
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error loading borrows: " + e.getMessage());
        }
    }

    private void loadRequests(Library library) {
        try (Reader reader = new FileReader(REQUESTS_FILE)) {
            Type listType = new TypeToken<List<BorrowRequest>>(){}.getType();
            List<BorrowRequest> loadedRequests = gson.fromJson(reader, listType);
            if (loadedRequests != null) {
                for (BorrowRequest request : loadedRequests) {
                    library.addBorrowRequest(request);
                }
            }
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error loading borrow requests: " + e.getMessage());
        }
    }
}