import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    public static final String[] wordList = { "hello", "major", "summary", "cucumber", "move", "harmony", "insurance",
            "kill", "undertake", "greet", "slice", "crop",
            "legend", "arena", "export", "confine", "fare", "shelter", "epicalyx", "treat", "sugar", "private",
            "infinite", "afford",
            "integrated", "straw", "waist", "wheel", "plaintiff", "increase", "carry", "innovation", "gravity",
            "payment", "quote", "fossil",
            "pound", "close", "unlawful", "percent", "remedy", "disability", "thank", "attractive", "section",
            "discover", "necklace",
            "width", "announcement", "tiptoe", "day", "Angel", "Eyeball", "Pizza", "Angry", "Fireworks", "Pumpkin",
            "Baby", "Flower",
            "Rainbow", "Beard", "Flying", "Recycle", "Bible", "Giraffe", "deodorant", "Bikini", "Glasses", "Snowflake",
            "Book",
            "zombie", "Stairs", "Bucket", "mechanic", "Starfish", "eclipse", "Igloo", "Strawberry", "Butterfly",
            "scrambled",
            "Sun", "Camera", "Lamp", "Tire", "Cat", "Lion", "Toast", "Church", "Mailbox", "Toothbrush", "Crayon",
            "Night", "Toothpaste",
            "Dolphin", "Nose", "Truck", "Egg", "Olympics", "Volleyball", "sasquatch", "Peanut" };
    public static int index = 0;
    public static String word;
    public static ArrayList<String> wordArray = new ArrayList<String>();
    public static ArrayList<String> displayedWord = new ArrayList<>();
    public static HashSet<Character> lettersGuessed = new HashSet<Character>();
    public static HashSet<Character> lettersMissed = new HashSet<Character>();

    public static void main(String[] args) {
        Boolean won = false;
        Scanner input = new Scanner(System.in);
        System.out.println("H A N G M A N");
        System.out.println("_____________");
        resetValues();
        while (true) { // Repeat game logic loop / initialize
            String tempDisplay = displayedWord.stream().reduce("", (total, next) -> total + next + " ").toString();
            printHangman(index, tempDisplay);
            int indexGuess = 0;
            System.out.println("\nGuess a letter or the word:");
            String userGuess = input.next().toLowerCase();
            indexGuess = guessLetter(userGuess);
            if (indexGuess == 402)
                System.out.println("You must guess a letter or word.");
            else if (indexGuess != 401)
                interpretGuess(indexGuess, userGuess);
            else
                System.out.println("You have already guessed that letter!");

            if (displayedWord.equals(wordArray)) {
                printHangman(index, displayedWord.stream().reduce("", (total, next) -> total + next + " ").toString());
                System.out.println("You guess the word correctly! It was " + word);
                won = true;
                break;
            }
            if (index == 7) {
                printHangman(index, tempDisplay);
                System.out.println("You Lost the game, the word was " + word + ".");
                break;
            }       
        }
        if (won) addHighScore(input);
        System.out.println("\nWould you like to play again? (Y to play again or any key to quit.)");
        String continueGame = input.next().toLowerCase();
        if (continueGame.equals("y") || continueGame.equals("yes")) main(null);
        else input.close();
    }

    public static int guessLetter(String guess) {
        // returns output code to interpretGuess / see if their is an issue with the
        // guess "aka user input"
        if (guess.length() > 1 && !Pattern.matches("[a-zA-Z]+", guess))
            return 402;
        else if (guess.length() > 1 && !word.equals(guess))
            return 400;
        else if (guess.length() > 1 && word.equals(guess))
            return 200;
        else if (lettersGuessed.contains(guess.charAt(0)))
            return 401;
        else if (!Pattern.matches("[a-zA-Z]+", guess))
            return 402;
        else if (wordArray.contains(guess))
            return 201;
        else
            return 404;
    }

    public static void addHighScore(Scanner input) {
        int score = (2 * word.length()) - lettersMissed.size();
        ArrayList<ArrayList<String>> highScores = new ArrayList<>();
        try {
            Files.readAllLines(Paths.get("highScores.txt")).stream().forEach(e -> {
                highScores.add(new ArrayList<String>(Arrays.asList(e.split(": "))));
            });
            System.out.println("Your score was: " + score);
            System.out.println("Please enter a name to record your score: ");
            String username = input.next();
            List<String> newScore = new ArrayList<String>();
            highScores.stream().forEach(e -> {
                newScore.add(e.get(0) + ": " + e.get(1));
            });
            if (score > Integer.parseInt(newScore.get(0).split(": ")[1])) {
                System.out.println("You got the new high score!");
                newScore.add(0, username + ": " + score);
            } else {
                highScores.stream().forEach(e -> {
                    String[] elem = new String[]{e.get(0), e.get(1)};
                    if (score > Integer.parseInt(elem[1])) {
                        if (!newScore.contains(username + ": " + score)) {
                            newScore.add(highScores.indexOf(e), username + ": " + score);
                        }
                    }
                });
                if (!newScore.contains(username + ": " + score)) newScore.add(username + ": " + score);
            }
            try {
                Files.write(Paths.get("highScores.txt"), newScore, StandardCharsets.UTF_8);
            } catch (IOException e1) {
                System.out.println("An Error has occurred while saving your score!");
            }
        } catch (IOException e) {
            System.out.println("Your score was: " + score);
            System.out.println("Please enter a name to record your score: ");
            String username = input.next();
            List<String> newScore = new ArrayList<>();
            newScore.add(username + ": " + score);
            try {
                Files.write(Paths.get("highScores.txt"), newScore, StandardCharsets.UTF_8);
            } catch (IOException e1) {
                System.out.println("An Error has occurred while saving your score!");
            }
        }  
    }

    public static boolean interpretGuess(int indexGuess, String userGuess) {
        // takes in the output code from guessLetter and adjusts values and lists as needed,
        // returns true or false true meaning the user guessed the word
        if (indexGuess == 404) {
            index++;
            lettersMissed.add(userGuess.charAt(0));
            lettersGuessed.add(userGuess.charAt(0));
        } else if (indexGuess == 400) {
            System.out.println("You did not guess the word right. Minus 2 points instead of one.");
            index += 2;
        } else {
            lettersGuessed.add(userGuess.charAt(0));
            displayedWord = new ArrayList<String>(wordArray.stream().map(e -> {
                Character elem = e.charAt(0);
                if (lettersGuessed.contains(elem)) return e;
                else return "_";
            }).toList());
            if (indexGuess == 200) {
                System.out.println("You guess the word correctly! It was " + word);
                return true;
            }
        }
        return false;
    }

    private static void printHangman(int index, String display) { // will not print it to file
        // Prints out the gallows based on how many letters you have guessed wrong
        List<String> hangmanDude = new ArrayList<String>();
        hangmanDude.add( " +---+");
        if (index >= 1)      hangmanDude.add(" O   |");
        else                 hangmanDude.add("     |");
        if (index >= 4)      hangmanDude.add("/|\\  |");
        else if (index >= 3) hangmanDude.add("/|   |");
        else if (index >= 2) hangmanDude.add(" |   |");
        else                 hangmanDude.add("     |");
        if (index >= 5)      hangmanDude.add(" |   |");
        else                 hangmanDude.add("     |");
        if (index >= 7)      hangmanDude.add("/ \\  |");
        else if (index >= 6) hangmanDude.add("/    |");
        else                 hangmanDude.add("     |");
        hangmanDude.add("    ===");
        hangmanDude.add("Missed Letters: " + lettersMissed.toString());
        hangmanDude.add(display);
        try {
            Files.write(Paths.get("hangman.txt"), hangmanDude, StandardCharsets.UTF_8);
            Files.readAllLines(Paths.get("hangman.txt")).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: Unable to read / write to hangman.txt");
        }
    }

    public static void resetValues() {
        // duh - self explanatory
        index = 0;
        word = wordList[(int) Math.floor(Math.random() * wordList.length)].toLowerCase();
        wordArray = new ArrayList<String>(Arrays.asList(word.split("")));
        displayedWord = new ArrayList<>(Arrays.asList(wordArray.stream()
            .reduce("", (total, next) -> total + "_")
            .split("")));
        lettersGuessed = new HashSet<Character>();
        lettersMissed = new HashSet<Character>();
       
    }

}