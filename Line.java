import java.util.List;
import java.util.ArrayList;

class Line implements Comparable<Line> {
    private List<String> words;

    public Line(List<String> words) {
        this.words = new ArrayList<>(words);
    }

    public List<Line> generateCircularShifts() {
        //TODO
        throw new UnsupportedOperationException("Unimplemented method 'generateCircularShifts'");
    }

    public List<String> getWords() {
        return new ArrayList<>(words);
    }

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
        for (int i = 0; i < minLength; i++) {
            int comparison = words.get(i).compareTo(o.words.get(i));
            if (comparison != 0) {
                return comparison;
            }
        }
        return Integer.compare(words.size(), o.words.size());
    }
}
