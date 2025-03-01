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
                List<Line> shifts = line.generateCircularShifts();   // Generate circular shifts for the line
                for (Line shift : shifts) { output.putLine(shift); } // Put each circular shift into the output pipe
            }
            
            output.closeForWriting(); // Close the output pipe for writing
        } catch (InterruptedException e) { e.printStackTrace(); } // Print stack trace if an InterruptedException occurs
    }
}
