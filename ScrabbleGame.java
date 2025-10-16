


  // Added method to check if the user used the correct letters given and added play again feature

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class ScrabbleGame {

    //a data structure to hold the dictionary of words
   
    private static List<Word> dictionary = new ArrayList<Word>();

    public static String getRandomLetters(int count) {
        Random rand = new Random();
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < count; i++) {
            char letter = (char) ('A' + rand.nextInt(26));
            letters.append(letter);
        }
        return letters.toString();
    }

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        Scanner fileScanner = null;

         try {
            fileScanner = new Scanner(new File("wordsWithDefs.txt"));
                while (fileScanner.hasNextLine()) {
                String word = fileScanner.next();
                String def = fileScanner.nextLine().trim();
                dictionary.add(new Word(word.toUpperCase(), def));
                }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            userInput.close();
            return;
        }


    // Sort dictionary for binary search
    dictionary.sort((w1, w2) -> w1.getWord().compareTo(w2.getWord()));

    String playAgain;
    do {
        String letters = getRandomLetters(4);
        System.out.println("Your letters are: " + letters);
        System.out.print("Form a word: ");
        String userWord = userInput.nextLine().trim().toUpperCase();

        System.out.println("Your word is: " + userWord);

        if (!canFormWord(letters, userWord)) {
            System.out.println("Did not use letters given.");
        } else {
            int index = binarySearch(dictionary, userWord);
            if (index != -1) {
                System.out.println("Word Valid. Definition: " + dictionary.get(index).getDefinition());
            } else {
                System.out.println("Word not found in dictionary. L.");
            }
        }

        System.out.print("Play again? (yes/no): ");
        playAgain = userInput.nextLine().trim().toLowerCase();

    } while (playAgain.equals("yes"));

    userInput.close();
    fileScanner.close();
}

        //write binary search method to find a word in the dictionary
        public static int binarySearch(List<Word> dict, String target) {
            int left = 0;
            int right = dict.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int cmp = dict.get(mid).getWord().compareTo(target);
                if (cmp == 0) {
                    return mid; // found
                } else if (cmp < 0) {
                    left = mid + 1; // search right half
                } else {
                    right = mid - 1; // search left half
                }
            }
            return -1; // not found
    }

    // method searches if user used correct letters
    public static boolean canFormWord(String letters, String word) {
        int[] letterCounts = new int[26];

        // Converts the letters/words to all uppercase
        letters = letters.toUpperCase();
        word = word.toUpperCase();

        for (char c : letters.toCharArray()) {
            letterCounts[c - 'A']++;
        }

        // checking if used letters are the same as ones given
        for (char c : word.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                if (--letterCounts[c - 'A'] < 0) {
                    return false;
                }
            }
            // invalid characters
            else {
                return false;
            }
        }
        return true;
    }
}

   
    
       



  

