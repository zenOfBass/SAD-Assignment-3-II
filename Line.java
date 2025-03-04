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
        List<String> thisWords = this.getWords();
        List<String> otherWords = other.getWords();
        int minSize = Math.min(thisWords.size(), otherWords.size());

        for (int i = 0; i < minSize; i++) {
            String thisWord = thisWords.get(i);
            String otherWord = otherWords.get(i);

            // Case-insensitive comparison
            int cmp = thisWord.compareToIgnoreCase(otherWord);
            if (cmp != 0)
                return cmp;

            // If identical case-insensitively, compare case-sensitive but favor lowercase
            for (int j = 0; j < Math.min(thisWord.length(), otherWord.length()); j++) {
                char c1 = thisWord.charAt(j);
                char c2 = otherWord.charAt(j);

                if (Character.toLowerCase(c1) == Character.toLowerCase(c2)) {
                    if (c1 != c2) {
                        return Character.isLowerCase(c1) ? -1 : 1; // Favor lowercase
                    }
                } else {
                    return Character.compare(Character.toLowerCase(c1), Character.toLowerCase(c2));
                }
            }

            // If one word is a prefix of another, the shorter word comes first
            if (thisWord.length() != otherWord.length()) {
                return Integer.compare(thisWord.length(), otherWord.length());
            }
        }

        // If words are identical up to minSize, compare by number of words
        return Integer.compare(thisWords.size(), otherWords.size());
    }
}
