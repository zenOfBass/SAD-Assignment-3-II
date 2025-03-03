import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// Line class representing a line of text
class Line implements Comparable<Line> {
    // List of words in the line
    private List<String> words;

    // Constructor to initialize the line with a list of words
    public Line(List<String> words) { this.words = new ArrayList<>(words); }

    // Constructor to initialize the line with a string
    public Line(String line) { 
        this.words = new ArrayList<>(               
            Arrays.asList(            /* Convert the array to a list for split method */
            line.split("\\s+")  /* "\\s+" matches one or more whitespace characters */
            )
        ); 
    }

    // Method to generate circular shifts of the line
    public List<Line> generateCircularShifts() {
        // List to store the circular shifts
        List<Line> shifts = new ArrayList<>();
        // Number of words in the line
        int numWords = words.size();

        if (words.isEmpty()) { return shifts; } // Return an empty list if the line is empty

        // Generate circular shifts by rotating the words
        for (int i = 0; i < numWords; i++) {
            List<String> shiftedWords = new ArrayList<>();
            for (int j = 0; j < numWords; j++) { shiftedWords.add(words.get((i + j) % numWords)); } // Rotate the words
            shifts.add(new Line(shiftedWords));                                                     // Add the shift to the list
        }

        return shifts; // return the list of circular shifts
    }

    // Method to get the words in the line
    public List<String> getWords() { return new ArrayList<>(words); }

    // Override the toString method to return a line object as a string
    @Override
    public String toString() { return String.join(" ", words); }

    // Override the compareTo method to compare lines lexicographically
    @Override
    public int compareTo(Line other) {
        // Get the list of words from both lines
        List<String> thisWords = this.getWords(), otherWords = other.getWords();
        // Get the minimum size of the two lists
        int minSize = Math.min(thisWords.size(), otherWords.size());

        for (int i = 0; i < minSize;) {
            // Compare ignoring case to group similar letters
            int caseInsensitiveComparison = thisWords.get(i).compareToIgnoreCase(otherWords.get(i));

            if (caseInsensitiveComparison != 0) { return caseInsensitiveComparison;} // Different letters, return the comparison
            else {
                // Same letter, different case
                String thisWord = thisWords.get(i), otherWord = otherWords.get(i);
                // Find the first position where characters differ if any
                int length = Math.min(thisWord.length(), otherWord.length());

                for (int j = 0; j < length; j++) {
                    // Get the characters at the current position
                    char thisChar = thisWord.charAt(j), otherChar = otherWord.charAt(j); 

                    if (Character.toLowerCase(thisChar) == Character.toLowerCase(otherChar)) {            // Same letter
                        if (thisChar != otherChar) { return Character.isLowerCase(thisChar) ? -1 : 1; }   // a < A
                    } else { return Character.toLowerCase(thisChar) - Character.toLowerCase(otherChar); } // Different letters
                }

                // If we get here, one word is a prefix of the other
                return thisWord.length() - otherWord.length();
            }
        }

        // If all words compared are the same, compare based on number of words
        return Integer.compare(thisWords.size(), otherWords.size());
    }
}
