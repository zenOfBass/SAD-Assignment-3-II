import java.util.List;
import java.util.ArrayList;

class Line implements Comparable<Line> {
    private List<String> words;

    public Line(List<String> words) {
        this.words = new ArrayList<>(words);
    }

    public List<Line> generateCircularShifts() {
        //TODO
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

    public List<String> getWords() {
        return new ArrayList<>(words);
    }

    @Override
    public int compareTo(Line o) {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}
