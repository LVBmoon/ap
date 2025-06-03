package ap.exercises.exe6.fetcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlFetcher {
    public static List<String> fetchHtml(String urlAddress) throws IOException {
        System.out.println("Fetching " + urlAddress + "...");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(urlAddress).openStream()))) {
            List<String> htmlLines = new ArrayList<>();
            String line;
            while ((line = in.readLine()) != null) {
                htmlLines.add(line);
            }
            System.out.println("Fetched " + urlAddress);
            return htmlLines;
        }
    }
}