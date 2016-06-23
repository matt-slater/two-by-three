import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Dictionary {

    HashSet<String> words;

    public Dictionary() {

        words = new HashSet<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/words.txt")));

        try {

            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean lookup(String word) {
        return words.contains(word);
    }
}
