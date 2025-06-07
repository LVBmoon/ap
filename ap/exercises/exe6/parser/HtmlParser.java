package ap.exercises.exe6.parser;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HtmlParser {

    public static String getFirstUrl(String htmlLine) {
        String url = null;
        int startIndex = htmlLine.indexOf("href=\"");

        if (startIndex >= 0) {
            try {
                int hrefLength = "href=\"".length();
                int endIndex = htmlLine.indexOf("\"", startIndex + hrefLength);
                url = htmlLine.substring(startIndex + hrefLength, endIndex);
            }
            catch (Exception e) {
                System.out.println("Error parsing URL: " + e.getMessage());
            }
        }
        return url;
    }

    private static List<String> getAllUrlsFromHtmlLinesStream(Stream<String> htmlLinesStream) throws IOException {
        List<String> urls = htmlLinesStream.flatMap(line -> {
                    List<String> lineUrls = new ArrayList<>();
                    Pattern pattern = Pattern.compile("href=[\"'](.*?)['\"]");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        lineUrls.add(matcher.group(1));
                    }
                    return lineUrls.stream();
                }).filter(s -> s != null).collect(Collectors.toList());
        return urls;
    }

    public static List<String> getAllUrlsFromFile(String filePath) throws IOException {
        return getAllUrlsFromHtmlLinesStream(Files.lines(Paths.get(filePath)));
    }

    public static List<String> getAllUrlsFromList(List<String> htmlLines) throws IOException {
        return getAllUrlsFromHtmlLinesStream(htmlLines.stream());
    }

    public static List<String> getImageUrlsFromList(List<String> htmlLines) {
        List<String> imageUrls = new ArrayList<>();
        Pattern pattern = Pattern.compile("<img\\s+[^>]*src=[\"']([^\"']+)[\"']");

        for (String line : htmlLines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                imageUrls.add(matcher.group(1));
            }
        }
        return imageUrls;
    }

    public static List<String> getMP3UrlsFromList(List<String> htmlLines) {
        List<String> mp3Urls = new ArrayList<>();
        Pattern pattern = Pattern.compile("href=[\"']([^\"']+\\.mp3)[\"']");

        for (String line : htmlLines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                mp3Urls.add(matcher.group(1));
            }
        }
        return mp3Urls;
    }
}