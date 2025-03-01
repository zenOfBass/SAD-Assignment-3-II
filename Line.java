import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// Line class representing a line of text
class Line implements Comparable<Line> {
    // List of words in the line
    private List<String> words;

    // Constructor to initialize the line with a list of words
    public Line(List<String> words) {
        this.words = new ArrayList<>(words);
    }

    // Constructor to initialize the line with a string
    public Line(String line) {
        this.words = new ArrayList<>(Arrays.asList(line.split("\\s+")));
    }

    // Method to generate circular shifts of the line
    public List<Line> generateCircularShifts() {
        List<Line> shifts = new ArrayList<>();
        if (words.isEmpty()) {
            return shifts;
        }

        int n = words.size();
        // Generate circular shifts by rotating the words
        for (int i = 0; i < n; i++) {
            List<String> shiftedWords = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                shiftedWords.add(words.get((i + j) % n));
            }
            shifts.add(new Line(shiftedWords));
        }

        return shifts;
    }

    // Method to get the words in the line
    public List<String> getWords() {
        return new ArrayList<>(words);
    }

    // Override the compareTo method to compare lines lexicographically
    @Override
    public int compareTo(Line o) {
        if (words.isEmpty() && o.words.isEmpty()) {
            return 0;
        }
        if (words.isEmpty()) {
            return -1;
        }
        if (o.words.isEmpty()) {
            return 1;
        }

        int minLength = Math.min(words.size(), o.words.size());
        // Compare words lexicographically
        for (int i = 0; i < minLength; i++) {
            int comparison = words.get(i).compareTo(o.words.get(i));
            if (comparison != 0) {
                return comparison;
            }
        }
        return Integer.compare(words.size(), o.words.size());
    }
}
