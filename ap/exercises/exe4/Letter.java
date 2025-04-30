package ap.exercises.exe4;

import java.util.ArrayList;
import java.util.List;

public class Letter {
    private String sender;
    private String recipient;
    private List<String> body;

    public Letter(String from, String to){
        this.sender = from;
        this.recipient = to;
        this.body = new ArrayList<>();
    }

    public void addLine(String line){
        body.add(line);
    }

    public String getText() {
        StringBuilder text = new StringBuilder();
        text.append("Dear ").append(recipient).append("\n");

        for (String line : body) {
            text.append(line).append("\n");
        }

        text.append("\nSincerely,\n").append(sender);
        return text.toString();
    }
}
