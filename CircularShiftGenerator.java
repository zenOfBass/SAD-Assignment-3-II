import java.util.List;

// CircularShiftGenerator filter
class CircularShiftGenerator implements Filter {
    // Input pipe to read lines from
    private Pipe input;
    // Output pipe to send circularly shifted lines
    private Pipe output;
    
    // Constructor to initialize the input and output pipes
    public CircularShiftGenerator(Pipe input, Pipe output) {
        this.input = input;
        this.output = output;
    }

    // Override the run method from the Filter interface
    @Override
    public void run() {
        try {
            Line line;
            // Read lines from the input pipe until it is closed
            while ((line = input.getLine()) != null) {
                // Generate circular shifts for the line
                List<Line> shifts = line.generateCircularShifts();
                // Put each circular shift into the output pipe
                for (Line shift : shifts) {
                    output.putLine(shift);
                }
            }
            // Close the output pipe for writing
            output.closeForWriting();
        } catch (InterruptedException e) {
            // Print stack trace if an InterruptedException occurs
            e.printStackTrace();
        }
    }
}
