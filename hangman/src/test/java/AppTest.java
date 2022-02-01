
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class AppTest {
    @Before
    public void init() {
        App.resetValues();
        App.word = "extra";
        for (String s : App.word.split("")) App.wordArray.add(s);
    }

    @Test
    public void guessCorrectWord() {
        assertEquals(200, App.guessLetter(App.word));
        assertEquals(true, App.interpretGuess(200, App.word));
    }

    @Test
    public void guessCorrectLetter() {
        for (String s : App.wordArray) App.displayedWord.add("_");
        ArrayList<String> testDisplay = new ArrayList<>();
        for (String s : App.wordArray) testDisplay.add("_");
        int i = 0;
        for (String s : App.wordArray) {
            if (s.equals("e")) {
                testDisplay.set(i, "e");
            }
            i++;
        }
        assertEquals(201, App.guessLetter("e"));
        assertEquals(false, App.interpretGuess(201, "e"));
        assertEquals(testDisplay, App.displayedWord);
        assertEquals(true, App.lettersGuessed.contains("e".charAt(0)));
    }

    @Test
    public void guessIncorrectLetter() {
        assertEquals(404, App.guessLetter("i"));
        assertEquals(false, App.interpretGuess(404, "i"));
        assertEquals(true, App.lettersMissed.contains("i".charAt(0)));
        assertEquals(true, App.lettersGuessed.contains("i".charAt(0)));
        assertEquals(1, App.index);
    }

    @Test
    public void guessIncorrectWord() {
        assertEquals(400, App.guessLetter("extended"));
        assertEquals(false, App.interpretGuess(400, "extended"));
        assertEquals(2, App.index);
    }

    //Test that loop the "guess a letter or word" prompt
    @Test
    public void guessIncorrectValue() {
        for (String s : App.word.split(""))
            App.wordArray.add(s);
        assertEquals(App.guessLetter("}"), 402);
        assertEquals(App.guessLetter("6"), 402);
        assertEquals(App.guessLetter("Extra2%"), 402);
    }

    @Test
    public void guessExistingLetter() {
        
        App.lettersGuessed.add("b".charAt(0));
        assertEquals(App.guessLetter("b"), 401);

    }
}
