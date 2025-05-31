package ap.exercises.exe5.store;

import ap.exercises.exe5.utils.DirectoryTools;

import java.io.PrintWriter;
import java.util.List;

public class HtmlFileManager {

    private String saveFileBasePath;
    private static int saveCounter=0;

    public HtmlFileManager(String saveFileBasePath) {
//        this.saveFileBasePath = DirectoryTools.createDirectoryWithTimeStamp(saveFileBasePath);

        this.saveFileBasePath = saveFileBasePath;
        DirectoryTools.createDirectory(saveFileBasePath);
    }

    public void save(List<String> lines) {
        try {
            String saveHtmlFileAddress = getSaveHtmlFileAddress();
            PrintWriter out = new PrintWriter(saveHtmlFileAddress);
            for (String line : lines) {
                out.println(line);
            }
            out.close();

            System.out.println("save counter: " + saveCounter);
        }catch (Exception e){
            System.out.println("save failed: " + e.getMessage());
        }
    }

    public String getSaveHtmlFileAddress(){
        saveCounter++;
        return saveFileBasePath +"/"+ saveCounter;
    }
}
