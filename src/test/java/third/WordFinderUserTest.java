package third;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WordFinderUserTest {
    final static String FIRST_SENTENCE = "One";
    final static String SECOND_SENTENCE = "Two";
    final static String THIRD_SENTENCE = "Three";

    private WordFinderUser wordFinderUser;
    private WordFinder wordFinder = Mockito.mock(WordFinder.class);

    @BeforeEach
    void prepareTest() {
        wordFinderUser = new WordFinderUser(wordFinder);
    }

    @Test
    void wordFinderEmptyList() {
        when(wordFinder.getSentences(any())).thenReturn(new HashSet<>());
        try {
            wordFinderUser.doWork("file://some", "word");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        verify(wordFinder, times(0)).checkInWordInSentence(any(), any());
        verify(wordFinder, times(0)).writeSentenceToResult(any());
    }

    @Test
    void wordFinderAllMathes() {
        when(wordFinder.getSentences(any())).thenReturn(new HashSet<>(
                Arrays.asList(FIRST_SENTENCE, SECOND_SENTENCE, THIRD_SENTENCE)));
        when(wordFinder.checkInWordInSentence(any(), any())).thenReturn(true);
        try {
            wordFinderUser.doWork("file://some", "word");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        verify(wordFinder, times(1)).writeSentenceToResult(FIRST_SENTENCE);
    }

    @Test
    void wordFinderNoMathes() {
        when(wordFinder.getSentences(any())).thenReturn(new HashSet<>(
                Arrays.asList(FIRST_SENTENCE, SECOND_SENTENCE, THIRD_SENTENCE)));
        when(wordFinder.checkInWordInSentence(any(), any())).thenReturn(false);
        try {
            wordFinderUser.doWork("file://some", "word");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        verify(wordFinder, times(0)).writeSentenceToResult(any());
    }
}
