package ap.exercises.exe6.store;

import ap.exercises.exe6.utils.DirectoryTools;

import java.io.PrintWriter;
import java.net.URL;
import java.util.List;

public class HtmlFileManager {
    private String htmlFileBasePath;
    private static int saveCounter = 0;

    public HtmlFileManager(String htmlFileBasePath) {
        this.htmlFileBasePath = htmlFileBasePath;
        DirectoryTools.createDirectory(htmlFileBasePath);
    }

    public void save(List<String> lines, String url) {
        if (lines == null || lines.isEmpty()) {
            System.out.println("Error: No content to save");
            return;
        }
        try {
            String saveHtmlFileAddress = getSaveHtmlFileAddress(url);
            try (PrintWriter out = new PrintWriter(saveHtmlFileAddress)) {
                for (String line : lines) {
                    out.println(line);
                }
            }
            System.out.println("Saved file: " + saveHtmlFileAddress + " (counter: " + saveCounter + ")");
        }
        catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private String getSaveHtmlFileAddress(String url) {
        saveCounter++;
        try {
            URL parsedUrl = new URL(url);
            String host = parsedUrl.getHost();
            String path = parsedUrl.getPath();
            String baseDomain = "znu.ac.ir";
            String subDomain = "";

            if (host.endsWith(baseDomain)) {
                String prefix = host.substring(0, host.length() - baseDomain.length());
                if (prefix.length() > 0 && prefix.endsWith(".")) {
                    prefix = prefix.substring(0, prefix.length() - 1);
                    subDomain = "_" + prefix;
                }
            }

            StringBuilder dirPath = new StringBuilder(htmlFileBasePath);
            if (!subDomain.isEmpty()) {
                dirPath.append("/").append(subDomain);
            }

            if (path != null && !path.isEmpty() && !path.equals("/")) {
                // reemove leading and trailing slashes
                path = path.replaceAll("^/+|/+$", "");
                if (!path.isEmpty()) {
                    dirPath.append("/").append(path.substring(0, path.lastIndexOf("/") > 0 ? path.lastIndexOf("/") : path.length()));
                }
            }

            String fullDirPath = dirPath.toString();
            DirectoryTools.createDirectory(fullDirPath);

            String fileName = path != null && path.lastIndexOf("/") < path.length() - 1
                    ? path.substring(path.lastIndexOf("/") + 1)
                    : "index";
            if (!fileName.endsWith(".html")) {
                fileName += ".html";
            }

            return fullDirPath + "/" + fileName;
        }
        catch (Exception e) {
            System.out.println("Error constructing save path for URL " + url + ": " + e.getMessage());
            return htmlFileBasePath + "/" + saveCounter + ".html";
        }
    }
}