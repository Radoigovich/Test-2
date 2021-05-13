import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private String textPage;

    public Map<String,Integer> getTextPage(String url) {
        Document page = null;
        try {
            page = Jsoup.parse(new URL(url), 30000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textPage = page.body().text();

        String[] words = textPage.toLowerCase().replaceAll("[,.!?;:()'\n\r\t\"]","").split(" ");

        Map<String, Integer> counterMapWords = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                Integer count = counterMapWords.get(word);
                if (count == null) {
                    count = 0;
                }
                counterMapWords.put(word, ++count);
            }
        }

        return counterMapWords;
    }

}