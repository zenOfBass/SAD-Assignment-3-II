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
        List<Line> shifts = new ArrayList<>();
        int numWords = words.size();

        if (words.isEmpty()) { return shifts; }

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

    // Override the compareTo method to compare lines lexicographically
    @Override
    public int compareTo(Line o) {
        if (words.isEmpty() && o.words.isEmpty()) { return 0; }
        if (words.isEmpty()) { return -1; }
        if (o.words.isEmpty()) { return 1; }

        int minLength = Math.min(words.size(), o.words.size()); // Get the minimum length of the two lines

        // Compare words lexicographically (by order in dictionary)
        for (int i = 0; i < minLength; i++) {
            int comparison = words.get(i).compareTo(o.words.get(i)); // Compare the words at index of i
            if (comparison != 0) { return comparison; }              // Return the comparison result if the words are different
        }
        
        return Integer.compare(words.size(), o.words.size()); // Return the comparison result based on the number of words
    }
}
