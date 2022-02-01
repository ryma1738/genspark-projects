import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    public static final String[] wordList = 
    {"hello", "major", "summary", "cucumber", "move", "harmony", "insurance", "kill", "undertake", "greet", "slice", "crop", 
    "legend", "arena", "export", "confine", "fare", "shelter", "epicalyx", "treat", "sugar", "private", "infinite", "afford", 
    "integrated", "straw", "waist", "wheel", "plaintiff", "increase", "carry", "innovation", "gravity", "payment", "quote", "fossil", 
    "pound", "close", "unlawful", "percent", "remedy", "disability", "thank", "attractive", "section", "discover", "necklace", 
    "width", "announcement", "tiptoe", "day", "Angel", "Eyeball", "Pizza", "Angry", "Fireworks", "Pumpkin", "Baby", "Flower", 
    "Rainbow", "Beard", "Flying", "Recycle", "Bible", "Giraffe", "deodorant", "Bikini", "Glasses", "Snowflake", "Book", 
    "zombie", "Stairs", "Bucket", "mechanic", "Starfish", "eclipse", "Igloo", "Strawberry", "Butterfly", "scrambled", 
    "Sun", "Camera", "Lamp", "Tire", "Cat", "Lion", "Toast", "Church", "Mailbox", "Toothbrush", "Crayon", "Night", "Toothpaste", 
    "Dolphin", "Nose", "Truck", "Egg", "Olympics", "Volleyball", "sasquatch", "Peanut"};
    public static int index = 0;
    public static String word;
    public static ArrayList<String> displayedWord = new ArrayList<String>();
    public static HashSet<Character> lettersGuessed = new HashSet<Character>();
    public static HashSet<Character> lettersMissed = new HashSet<Character>();
    public static ArrayList<String> wordArray = new ArrayList<String>();
    public static void main( String[] args ) {
        Scanner input = new Scanner(System.in);
        while (true) { //Repeat game logic loop / initialize
            System.out.println("H A N G M A N");
            System.out.println("_____________");
            resetValues();
            for (String s : word.split("")) wordArray.add(s);
            for (String s : wordArray) displayedWord.add("_");

            while (true) { //main game logic
                String tempDisplay = "";
                for (String s : displayedWord) tempDisplay += s + " ";
                printHangman(index);
                System.out.println("Missed Letters: " + lettersMissed.toString());
                System.out.println(tempDisplay);
                int indexGuess = 0;
                String userGuess;
                while (true) { 
                    System.out.println("\nGuess a letter or the word:");
                    userGuess = input.next().toLowerCase();
                    indexGuess = guessLetter(userGuess);
                    if (indexGuess == 402) System.out.println("You must guess a letter or word.");
                    else if (indexGuess != 401) break;
                    else System.out.println("You have already guessed that letter!");
                }

                if (interpretGuess(indexGuess, userGuess)) {
                    break;
                } if (displayedWord.equals(wordArray)) {
                    System.out.println("You guess the word correctly! It was " + word);
                    break;
                } if (index == 7) {
                    printHangman(index);
                    System.out.println("You Lost the game, the word was " + word + ".");
                    break;
                }
            }
            System.out.println("\nWould you like to play again? (N or any key to continue)");
            String continueGame = input.next().toLowerCase();
            if (continueGame.equals("n") || continueGame.equals("no")) break;
        }
        input.close();
    }

    public static int guessLetter(String guess) {
        //returns output code to interpretGuess / see if their is an issue with the guess "aka user input"
        if (guess.length() > 1 && !Pattern.matches("[a-zA-Z]+", guess)) return 402;
        else if (guess.length() > 1 && !word.equals(guess)) return 400;
        else if (guess.length() > 1 && word.equals(guess)) return 200;
        else if (lettersGuessed.contains(guess.charAt(0))) return 401;
        else if (!Pattern.matches("[a-zA-Z]+", guess)) return 402;
        else if (wordArray.contains(guess)) return 201;
        else return 404;
    }

    public static boolean interpretGuess(int indexGuess, String userGuess) {
        //takes in the output code from guessLetter and adjusts values and lists as needed, 
        //returns true or false true meaning the user guessed the word
        if (indexGuess == 404) {
            index++;
            lettersMissed.add(userGuess.charAt(0));
            lettersGuessed.add(userGuess.charAt(0));
        } else if (indexGuess == 400) {
            System.out.println("You did not guess the word right. Minus 2 points instead of one.");
            index += 2;
        } else if (indexGuess == 200) {
            System.out.println("You guess the word correctly!");
            return true;
        } else {
            int i = 0;
            for (String s : wordArray) {
                if (s.equals(userGuess)) {
                    displayedWord.set(i, userGuess);
                }
                i++;
            }
            lettersGuessed.add(userGuess.charAt(0));
        } 
        return false;
    }

    private static void printHangman(int index) {
        //Prints out the gallows based on how many letters you have guessed wrong
        System.out.println("\n +---+");
        if (index >= 1) System.out.println(" O   |");
        else System.out.println("     |");
        if (index >= 4) System.out.println("/|\\  |");
        else if (index >= 3) System.out.println("/|   |");
        else if (index >= 2) System.out.println(" |   |");
        else System.out.println("     |");
        if (index >= 5) System.out.println(" |   |");
        else System.out.println("     |");
        if (index >= 7) System.out.println("/ \\  |");
        else if (index >=6) System.out.println("/    |");
        else System.out.println("     |");
        System.out.println("    ===");
    }

    public static void resetValues() {
        //duh - self explanatory
        index = 0;
        word = wordList[(int) Math.floor(Math.random() * 101)].toLowerCase();
        lettersGuessed = new HashSet<Character>();
        lettersMissed = new HashSet<Character>();
        displayedWord = new ArrayList<String>();
        wordArray = new ArrayList<String>();
    }

}