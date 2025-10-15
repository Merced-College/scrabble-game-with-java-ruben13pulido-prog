import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScrabbleGame {
    
       
        // a data structure to hold the words
        private static List<Word> dictionary = new ArrayList<Word>();

        public static void main(String[] args) {

        //read in my text file of words and definitons into
        // my arraylist
        Scanner in = null;
        try {
            in = new Scanner(new File("wordsWithDefs.txt"));
            while (in.hasNextLine()) {
                String word = in.nextLine();
                String def = in.nextLine().trim();
                dictionary.add(new Word(word, def));
          
            }
            //close the scanner after we use it to avoid resource leaks
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } finally {
            if (in != null) {
                in.close();
            }
        } 

        //print out the dictionary
        for (Word w : dictionary) {
            System.out.println(w);
        }

    } // end main
    //binary search for a word in the dictionary
     public static int binarySearch(List<Word> list, String target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = list.get(mid).getWord().compareTo(target);

            if (cmp == 0) {
                return mid; // Word found
            } else if (cmp < 0) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }
        return -1; // Word not found 
    } // end binarySearch
} // end class
