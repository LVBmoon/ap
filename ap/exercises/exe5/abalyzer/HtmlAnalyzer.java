package ap.exercises.exe5.abalyzer;

import ap.exercises.exe5.Conf;
import ap.exercises.exe5.parser.HtmlParser;
import ap.exercises.exe5.utils.DirectoryTools;
import ap.exercises.exe5.utils.FileTools;
import ap.exercises.exe5.utils.StringCounter;
//---
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HtmlAnalyzer {
    private static List<String> fileList = DirectoryTools.getFilesAbsolutePathInDirectory(Conf.SAVE_DIRECTORY);

    public static List<String> getAllUrls() {
        List<String> urls = fileList.stream()
                .map(fileAddress -> FileTools.getTextFileLines(fileAddress))
                .filter(s -> s != null)
                .flatMap(s -> s.stream())
                .map(s -> HtmlParser.getFirstUrl(s))
                .filter(s -> s != null)
                .filter(s -> s.length() > 1)
                .collect(Collectors.toList());
        return urls;
    }

    public static List<String> getTopUrls(int k) {
        Map<String, Long> urlCount = getAllUrls().stream()
                .collect(Collectors.groupingBy(
                        s -> s,
                        Collectors.counting()
                ));

        List<String> topUrls = urlCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(k)
                .map(s -> s.getKey())
                .collect(Collectors.toList());

        return topUrls;
    }

    public static void printTopCountUrls(int k) {
        StringCounter urlCounter = new StringCounter(); // Changed from ObjectCounter
        getAllUrls().forEach(s -> urlCounter.add(s));
        for (Map.Entry<String, Integer> urlCountEntry : urlCounter.getTop(k)) {
            System.out.println(urlCountEntry.getKey() + " -> " + urlCountEntry.getValue());
        }
    }

    public static void main(String[] args) {
        HtmlAnalyzer.printTopCountUrls(10);
        System.out.println("____________________");
        HtmlAnalyzer.getTopUrls(10).forEach(s -> System.out.println(s));

    }
}
