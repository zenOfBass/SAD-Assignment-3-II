// InputReader filter
class InputReader implements Filter {
    // Output pipe to send processed lines
    private Pipe output;
    // Text input to be processed
    private String inputText;
    
    // Constructor to initialize the output pipe
    public InputReader(Pipe input, Pipe output) {
        this.output = output;
    }

    // Method to read lines from the input text
    public void readLines(String text) {
        this.inputText = text;
    }

    // Override the run method from the Filter interface
    @Override
    public void run() {
        try {
            // Split the input text into lines
            String[] lines = inputText.split("\n");
            // Process each line
            for (String line : lines) {
                // Check if the line is not empty after trimming
                if (!line.trim().isEmpty()) {
                    // Put the trimmed line into the output pipe
                    output.putLine(new Line(line.trim()));
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
