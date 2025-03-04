class OutputFormatter implements Filter {
    // Input pipe to read lines from
    private Pipe input;
    
    // Constructor to initialize the input pipe
    public OutputFormatter(Pipe input, Pipe output) { this.input = input; }

    // Override the run method from the Filter interface
    @Override
    public void run() {
        try {
            Line line;
            
            // Read lines from the input pipe until it is closed
            while ((line = input.getLine()) != null) {
                System.out.println(line);
            }
        } catch (InterruptedException e) { e.printStackTrace(); } // Print stack trace if an InterruptedException occurs
    }
}
