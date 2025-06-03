package ap.exercises.exe6.fetcher;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MP3Downloader {

    public static void downloadMP3(String fileUrl, String savePath) throws IOException {
        // Validate inputs
        if (fileUrl == null || fileUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        if (savePath == null || savePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Save path cannot be null or empty");
        }
        if (!savePath.toLowerCase().endsWith(".mp3")) {
            throw new IllegalArgumentException("Save path must end with .mp3 extension");
        }

        Path outputPath = Paths.get(savePath);
        Files.createDirectories(outputPath.getParent());

        HttpURLConnection connection = null;
        BufferedInputStream in = null;
        FileOutputStream fileOut = null;

        try {
            URL url = new URL(fileUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check for successful response
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Server returned HTTP " + responseCode);
            }

            // Check content type
            String contentType = connection.getContentType();
            if (contentType != null && !contentType.contains("audio/mpeg")) {
                System.err.println("Warning: Content-Type is " + contentType + " - expected audio/mpeg");
            }

            // Get file size for progress tracking
            int fileSize = connection.getContentLength();

            // Download file
            in = new BufferedInputStream(connection.getInputStream());
            fileOut = new FileOutputStream(savePath);

            byte[] buffer = new byte[8192];
            int bytesRead;
            int totalRead = 0;

            System.out.println("Downloading MP3...");
            while ((bytesRead = in.read(buffer)) != -1) {
                fileOut.write(buffer, 0, bytesRead);
                totalRead += bytesRead;

                // Print progress if we know the file size
                if (fileSize > 0) {
                    int progress = (int) ((totalRead * 100.0) / fileSize);
                    System.out.printf("\rProgress: %d%%", progress);
                }
            }
            System.out.println("\nDownload complete!");

        } finally {
            // Clean up resources
            if (in != null) in.close();
            if (fileOut != null) fileOut.close();
            if (connection != null) connection.disconnect();
        }
    }

    public static void main(String[] args) {
        try {
            String mp3Url = "https://dl.musicdel.ir/Music/1400/05/gholamhossein_banan_ey_iran%20ey%20marze%20por%20gohar%20128.mp3";
            String saveLocation = "fetched_music/ey_iran.mp3";

            downloadMP3(mp3Url, saveLocation);
            System.out.println("MP3 saved to: " + saveLocation);

        } catch (Exception e) {
            System.err.println("Error downloading MP3: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
