package ap.exercises.exe6.fetcher;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImageDownloader {
    public static void downloadImage(String imageUrl, String outputPath) throws IOException {
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be null or empty");
        }
        if (outputPath == null || outputPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Output path cannot be null or empty");
        }

        URL url = new URL(imageUrl);
        try (InputStream in = url.openStream()) {
            Path output = Paths.get(outputPath);
            try {
                Files.createDirectories(output.getParent());
            }
            catch (Exception e){
                System.out.println("Failed to create directory: " + outputPath);
            }
            Files.copy(in, output, StandardCopyOption.REPLACE_EXISTING);
        }
    }


    public static void main(String[] args) {
        try {
            String imageUrl = "https://www.znu.ac.ir/files/uploaded/news-pic/stories/thumbsVertical-450-600/pr-132-pr-entesab404-03-04.jpg";
            String saveFileAddress = "fetched_images/test.jpg";
            System.out.println("Downloading image from: " + imageUrl);
            downloadImage(imageUrl, saveFileAddress);
            System.out.println("Image successfully saved to: " + saveFileAddress);

        }
        catch (IOException e) {
            System.err.println("Failed to download image: " + e.getMessage());
            e.printStackTrace();
        }
    }
}