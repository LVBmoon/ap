package ap.exercises.exe5.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringCounter {
    private static class Entry {
        String url;
        int count;

        Entry(String url) {
            this.url = url;
            this.count = 1;
        }
    }

    private List<Entry> stringCounter; // ArrayList to store entries to count then :|

    public StringCounter() {
        this.stringCounter = new ArrayList<>();
    }

    public void add(String strItem) {
        if (strItem != null && !strItem.trim().isEmpty()) {
            // Search for existing string
            for (Entry entry : stringCounter) {
                if (entry.url.equals(strItem)) {
                    entry.count++;
                    return;
                }
            }
            stringCounter.add(new Entry(strItem));
        }
    }

//    public int getCount(String item) {
//        for (Entry entry : stringCounter) {
//            if (item != null && entry.url.equals(item)) {
//                return entry.count;
//            }
//        }
//        return 0;
//    }

    public Map<String, Integer> getAllCounts() {
        Map<String, Integer> result = new HashMap<>();
        for (Entry entry : stringCounter) {
            result.put(entry.url, entry.count);
        }
        return result;
    }

    public List<Map.Entry<String, Integer>> getTop(int k) {
        return getAllCounts().entrySet().stream()
                .sorted((a, b) -> -a.getValue().compareTo(b.getValue()))
                .limit(k)
                .collect(Collectors.toList());
    }

//    public int getTotalItems() {
//        return stringCounter.stream().mapToInt(entry -> entry.count).sum();
//    }

//    public void clear() {
//        stringCounter.clear();
//    }
}