package ap.exercises.exe6.store;

import ap.exercises.exe6.utils.DirectoryTools;

import java.io.PrintWriter;
import java.util.List;

public class HtmlFileManager {
    private String saveFileBasePath;
    private static int saveCounter = 0;

    public HtmlFileManager(String saveFileBasePath) {
        this.saveFileBasePath = saveFileBasePath;
        DirectoryTools.createDirectory(saveFileBasePath);
    }

    public void save(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            System.out.println("Error: No content to save");
            return;
        }
        try {
            String saveHtmlFileAddress = getSaveHtmlFileAddress();
            try (PrintWriter out = new PrintWriter(saveHtmlFileAddress)) {
                for (String line : lines) {
                    out.println(line);
                }
            }
            System.out.println("Saved file: " + saveHtmlFileAddress + " (counter: " + saveCounter + ")");
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private String getSaveHtmlFileAddress() {
        saveCounter++;
        return saveFileBasePath + "/" + saveCounter + ".html";
    }
}