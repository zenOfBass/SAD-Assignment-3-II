class OutputFormatter implements Filter {
    // Input pipe to read lines from
    private Pipe input;
    
    // Constructor to initialize the input pipe
    public OutputFormatter(Pipe input, Pipe output) {
        this.input = input;
    }

    // Override the run method from the Filter interface
    @Override
    public void run() {
        try {
            // Print the header for the KWIC Index System Output
            System.out.println("KWIC Index System Output:");
            System.out.println("-------------------------");

            Line line;
            int count = 1;
            // Read lines from the input pipe until it is closed
            while ((line = input.getLine()) != null) {
                // Print each line with a count
                System.out.println(count + ". " + line);
                count++;
            }

            // Print the footer for the KWIC Index System Output
            System.out.println("-------------------------");
        } catch (InterruptedException e) {
            // Print stack trace if an InterruptedException occurs
            e.printStackTrace();
        }
    }
}
