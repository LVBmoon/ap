package ap.exercises.exe6;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String domainAddress = Conf.DOMAIN_ADDRESS;
        String htmlSavePath = Conf.HTML_DIRECTORY;
        DomainHtmlScraper domainHtmlScraper = new DomainHtmlScraper(domainAddress, htmlSavePath);
        domainHtmlScraper.start(htmlSavePath);
    }
}