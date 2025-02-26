import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Sorter filter
class Sorter implements Filter {
    // Input pipe to read lines from
    private Pipe input;
    // Output pipe to send sorted lines
    private Pipe output;

    // Constructor to initialize the input and output pipes
    public Sorter(Pipe input, Pipe output) {
        this.input = input;
        this.output = output;
    }

    // Override the run method from the Filter interface
    @Override
    public void run() {
        try {
            // List to store all circular shifts
            List<Line> allShifts = new ArrayList<>();
            Line line;
            
            // Read lines from the input pipe until it is closed
            while ((line = input.getLine()) != null) {
                allShifts.add(line);
            }
            
            // Sort all the collected shifts
            Collections.sort(allShifts);
            
            // Put each sorted shift into the output pipe
            for (Line shift : allShifts) {
                output.putLine(shift);
            }
            
            // Close the output pipe for writing
            output.closeForWriting();
        } catch (InterruptedException e) {
            // Print stack trace if an InterruptedException occurs
            e.printStackTrace();
        }
    }
}
