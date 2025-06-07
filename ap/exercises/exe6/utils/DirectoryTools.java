package ap.exercises.exe6.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class DirectoryTools {

    private DirectoryTools() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    public static boolean directoryExists(String directoryPath) {
        if (directoryPath == null || directoryPath.trim().isEmpty()) {
            return false;
        }
        Path path = Paths.get(directoryPath);
        return Files.exists(path) && Files.isDirectory(path);
    }

    public static boolean createDirectory(String directoryPath) {
        if (directoryPath == null || directoryPath.trim().isEmpty()) {
            return false;
        }
        try {
            Path path = Paths.get(directoryPath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            return true;
        }
        catch (IOException e) {
            System.err.println("Directory creation failed: " + e.getMessage());
            return false;
        }
    }

    public static List<File> getFilesInDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Path is not a directory: " + directoryPath);
        }
        List<File> files = new ArrayList<>();
        collectFilesRecursively(dir, files);
        return files;
    }

    private static void collectFilesRecursively(File dir, List<File> files) {
        File[] dirContents = dir.listFiles();
        if (dirContents != null) {
            for (File file : dirContents) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    collectFilesRecursively(file, files);
                }
            }
        }
    }

    public static List<String> getFilesAbsolutePathInDirectory(String directoryPath) {
        return getFilesInDirectory(directoryPath).stream()
                .map(s -> s.getAbsolutePath())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String testDir = "./test/directory";
        System.out.println("Directory exists (before): " + directoryExists(testDir));
        System.out.println("Creation result: " + createDirectory(testDir));
        System.out.println("Directory exists (after): " + directoryExists(testDir));
    }
}