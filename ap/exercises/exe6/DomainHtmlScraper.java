package ap.exercises.exe6;

import ap.exercises.exe6.fetcher.HtmlFetcher;
import ap.exercises.exe6.parser.HtmlParser;
import ap.exercises.exe6.store.HtmlFileManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

public class DomainHtmlScraper {
    private String domainAddress;
    private Queue<String> queue;
    private HtmlFileManager htmlFileManager;
    private Set<String> visited;

    public DomainHtmlScraper(String domainAddress, String savePath) {
        this.domainAddress = domainAddress.endsWith("/") ? domainAddress : domainAddress + "/";
        this.queue = new LinkedList<>();
        this.htmlFileManager = new HtmlFileManager(savePath);
        this.visited = new HashSet<>();
    }

    private String resolveUrl(String baseUrl, String relativeUrl) {
        try {
            return new URL(new URL(baseUrl), relativeUrl).toString();
        }
        catch (Exception e) {
            return null;
        }
    }

    public void start(String savePath) throws IOException {
        PrintWriter imageWriter = new PrintWriter(savePath + "/images.txt");
        List<String> htmlLines = HtmlFetcher.fetchHtml(domainAddress);
        htmlFileManager.save(htmlLines, domainAddress);
        visited.add(domainAddress);
        List<String> imageUrls = HtmlParser.getImageUrlsFromList(htmlLines);

        for (String imageUrl : imageUrls) {
            String resolvedImageUrl = resolveUrl(domainAddress, imageUrl);
            if (resolvedImageUrl != null) {
                imageWriter.println(resolvedImageUrl);
            }
        }

        List<String> urls = HtmlParser.getAllUrlsFromList(htmlLines);
        Set<String> validUrls = new HashSet<>();

        for (String url : urls) {
            String resolvedUrl = resolveUrl(domainAddress, url);
            if (resolvedUrl != null && resolvedUrl.contains("znu.ac.ir")) {
                validUrls.add(resolvedUrl);
            }
        }
        queue.addAll(validUrls);
        int counter = 1;

        while (!queue.isEmpty()) {
            String url = queue.remove();
            if (visited.contains(url)) continue;
            visited.add(url);
            counter++;
            try {
                htmlLines = HtmlFetcher.fetchHtml(url);
                htmlFileManager.save(htmlLines, url);
                imageUrls = HtmlParser.getImageUrlsFromList(htmlLines);

                for (String imageUrl : imageUrls) {
                    String resolvedImageUrl = resolveUrl(url, imageUrl);
                    if (resolvedImageUrl != null) {
                        imageWriter.println(resolvedImageUrl);
                    }
                }

                urls = HtmlParser.getAllUrlsFromList(htmlLines);
                validUrls.clear();

                for (String nextUrl : urls) {
                    String resolvedUrl = resolveUrl(url, nextUrl);
                    if (resolvedUrl != null && resolvedUrl.contains("znu.ac.ir")) {
                        validUrls.add(resolvedUrl);
                    }
                }
                queue.addAll(validUrls);
            }
            catch (Exception e) {
                System.out.println("Error fetching " + url + ": " + e.getMessage());
            }
            System.out.println("[" + counter + "] " + url + " fetched and saved (queue size: " + queue.size() + ")");
        }
        imageWriter.close();
        System.out.println("Scraping completed.");
    }
}