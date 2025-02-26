import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Sorter implements Filter {
    private Pipe input;
    private Pipe output;

    public Sorter(Pipe input, Pipe output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        try {
            List<Line> allShifts = new ArrayList<>();
            Line line;
            
            while ((line = input.getLine()) != null) {
                allShifts.add(line);
            }
            
            Collections.sort(allShifts);
            
            for (Line shift : allShifts) {
                output.putLine(shift);
            }
            
            output.closeForWriting();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
