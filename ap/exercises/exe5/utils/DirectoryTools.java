package ap.exercises.exe5.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A utility class for directory operations including existence checks and creation.
 * <p>
 * This class provides thread-safe static methods for common directory operations
 * using Java's NIO.2 API ({@code java.nio.file} package).
 * </p>
 *
 * @author YourName
 * @version 1.0
 * @since 1.8
 */
public final class DirectoryTools {

    private static final DateTimeFormatter TIMESTAMP_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    /**
     * Private constructor to prevent instantiation.
     */
    private DirectoryTools() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    /**
     * Checks whether a directory exists at the specified path.
     *
     * @param directoryPath the path to check (absolute or relative)
     * @return {@code true} if the directory exists and is actually a directory,
     *         {@code false} otherwise (including when path is null/empty)
     * @throws SecurityException if read access to the directory is denied
     *
     * @example
     * <pre>{@code
     * boolean exists = DirectoryTools.directoryExists("/tmp/test");
     * }</pre>
     */
    public static boolean directoryExists(String directoryPath) {
        if (directoryPath == null || directoryPath.trim().isEmpty()) {
            return false;
        }

        Path path = Paths.get(directoryPath);
        return Files.exists(path) && Files.isDirectory(path);
    }

    /**
     * Creates a directory including all necessary parent directories.
     *
     * @param directoryPath the path of the directory to create
     * @return {@code true} if the directory was created or already exists,
     *         {@code false} if creation failed (including when path is null/empty)
     * @throws SecurityException if write access is denied
     *
     * @example
     * <pre>{@code
     * boolean success = DirectoryTools.createDirectory("/tmp/new/path");
     * }</pre>
     */
    public static boolean createDirectory(String directoryPath) {
        if (directoryPath == null || directoryPath.trim().isEmpty()) {
            return false;
        }

        try {
            Path path = Paths.get(directoryPath);
            if (!directoryExists(directoryPath)) {
                Files.createDirectories(path);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Directory creation failed: " + e.getMessage());
            return false;
        }
    }

    public static String createDirectoryWithTimeStamp(String directroyPath){
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        String finalPath = directroyPath + "__" + timestamp;
        createDirectory(directroyPath+"__"+timestamp);
        return finalPath;
    }

    /**
     * Gets all regular files (non-directories) in the specified directory
     * @param directoryPath Path to the directory to scan
     * @return List of File objects for each file in the directory
     * @throws IllegalArgumentException if the path is not a directory
     */
    public static List<File> getFilesInDirectory(String directoryPath) {
        File dir = new File(directoryPath);

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Path is not a directory: " + directoryPath);
        }

        List<File> files = new ArrayList<>();
        File[] dirContents = dir.listFiles();

        if (dirContents != null) {
            for (File file : dirContents) {
                if (file.isFile()) {
                    files.add(file);
                }
            }
        }

        return files;
    }


    public static List<String> getFilesAbsolutePathInDirectory(String directoryPath) {
        return DirectoryTools.getFilesInDirectory(directoryPath).stream()
                .map(s -> s.getAbsolutePath())
                .collect(Collectors.toList());
    }

    /**
     * Demonstrates usage of DirectoryTools methods.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        String testDir = "./test/directory";

        System.out.println("Directory exists (before): " + directoryExists(testDir));
        System.out.println("Creation result: " + createDirectory(testDir));
        System.out.println("Directory exists (after): " + directoryExists(testDir));
    }
}
