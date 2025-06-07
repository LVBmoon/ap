package ap.exercises.exe6;

import ap.exercises.exe6.fetcher.HtmlFetcher;
import ap.exercises.exe6.fetcher.ImageDownloader;
import ap.exercises.exe6.fetcher.MP3Downloader;
import ap.exercises.exe6.parser.HtmlParser;
import ap.exercises.exe6.store.HtmlFileManager;
import ap.exercises.exe6.utils.DirectoryTools;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class DomainHtmlScraper {
    private String domainAddress;
    private Queue<String> queue;
    private HtmlFileManager htmlFileManager;
    private Set<String> visited;

    public DomainHtmlScraper(String domainAddress, String htmlSavePath) {
        this.domainAddress = domainAddress.endsWith("/") ? domainAddress : domainAddress + "/";
        this.queue = new LinkedList<>();
        this.htmlFileManager = new HtmlFileManager(htmlSavePath);
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

    private String getFileNameFromUrl(String url) {
        try {
            String path = new URL(url).getPath();
            return path.substring(path.lastIndexOf("/") + 1);
        }
        catch (Exception e) {
            return UUID.randomUUID().toString();
        }
    }

    public void start(String htmlSavePath) throws IOException {
        DirectoryTools.createDirectory(Conf.IMAGE_DIRECTORY);
        DirectoryTools.createDirectory(Conf.SONG_DIRECTORY);
        List<String> htmlLines = HtmlFetcher.fetchHtml(domainAddress);
        htmlFileManager.save(htmlLines, domainAddress);
        visited.add(domainAddress);
        List<String> imageUrls = HtmlParser.getImageUrlsFromList(htmlLines);

        for (String imageUrl : imageUrls) {
            String resolvedImageUrl = resolveUrl(domainAddress, imageUrl);
            if (resolvedImageUrl != null && resolvedImageUrl.contains("znu.ac.ir")) {
                try {
                    String fileName = getFileNameFromUrl(resolvedImageUrl);
                    if (!fileName.toLowerCase().endsWith(".jpg") && !fileName.toLowerCase().endsWith(".png")) {
                        fileName += ".jpg";
                    }
                    ImageDownloader.downloadImage(resolvedImageUrl, Conf.IMAGE_DIRECTORY + "/" + fileName);
                } catch (Exception e) {
                    System.out.println("Error downloading image " + resolvedImageUrl + ": " + e.getMessage());
                }
            }
        }

        List<String> mp3Urls = HtmlParser.getMP3UrlsFromList(htmlLines);
        for (String mp3Url : mp3Urls) {
            String resolvedMp3Url = resolveUrl(domainAddress, mp3Url);
            if (resolvedMp3Url != null && resolvedMp3Url.contains("znu.ac.ir")) {
                try {
                    String fileName = getFileNameFromUrl(resolvedMp3Url);
                    if (!fileName.toLowerCase().endsWith(".mp3")) {
                        fileName += ".mp3";
                    }
                    MP3Downloader.downloadMP3(resolvedMp3Url, Conf.SONG_DIRECTORY + "/" + fileName);
                } catch (Exception e) {
                    System.out.println("Error downloading MP3 " + resolvedMp3Url + ": " + e.getMessage());
                }
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
                Thread.sleep(Conf.DELAY_SECONDS * 1000);// as wanted
                htmlLines = HtmlFetcher.fetchHtml(url);
                htmlFileManager.save(htmlLines, url);
                imageUrls = HtmlParser.getImageUrlsFromList(htmlLines);

                for (String imageUrl : imageUrls) {
                    String resolvedImageUrl = resolveUrl(url, imageUrl);
                    if (resolvedImageUrl != null && resolvedImageUrl.contains("znu.ac.ir")) {
                        try {
                            String fileName = getFileNameFromUrl(resolvedImageUrl);
                            if (!fileName.toLowerCase().endsWith(".jpg") && !fileName.toLowerCase().endsWith(".png")) {
                                fileName += ".jpg";
                            }
                            ImageDownloader.downloadImage(resolvedImageUrl, Conf.IMAGE_DIRECTORY + "/" + fileName);
                        }
                        catch (Exception e) {
                            System.out.println("Error downloading image " + resolvedImageUrl + ": " + e.getMessage());
                        }
                    }
                }

                mp3Urls = HtmlParser.getMP3UrlsFromList(htmlLines);
                for (String mp3Url : mp3Urls) {
                    String resolvedMp3Url = resolveUrl(url, mp3Url);
                    if (resolvedMp3Url != null && resolvedMp3Url.contains("znu.ac.ir")) {
                        try {
                            String fileName = getFileNameFromUrl(resolvedMp3Url);
                            if (!fileName.toLowerCase().endsWith(".mp3")) {
                                fileName += ".mp3";
                            }
                            MP3Downloader.downloadMP3(resolvedMp3Url, Conf.SONG_DIRECTORY + "/" + fileName);
                        }
                        catch (Exception e) {
                            System.out.println("Error downloading MP3 " + resolvedMp3Url + ": " + e.getMessage());
                        }
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

        System.out.println("Scraping completed.");
    }
}