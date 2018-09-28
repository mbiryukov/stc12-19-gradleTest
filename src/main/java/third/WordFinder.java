package third;

import java.net.URL;
import java.util.HashSet;

public interface WordFinder {
    HashSet<String> getSentences(URL source);

    boolean checkInWordInSentence(String sentence, String word);

    void writeSentenceToResult(String sentence);
}
