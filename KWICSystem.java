public class KWICSystem {
    public static void main(String[] args) {
        try {
            // Example usage with sample input
            String input = "The quick brown fox\nJumps over the lazy dog";

            // Create and connect filters through pipes
            Pipe inputToCShift = new Pipe();
            Pipe cShiftToSorter = new Pipe();
            Pipe sorterToOutput = new Pipe();

            // Create the filters
            Filter inputReader = new InputReader(null, inputToCShift);
            Filter circularShiftGenerator = new CircularShiftGenerator(inputToCShift, cShiftToSorter);
            Filter sorter = new Sorter(cShiftToSorter, sorterToOutput);
            Filter outputFormatter = new OutputFormatter(sorterToOutput, null);

            // Process the input
            ((InputReader) inputReader).readLines(input);

            // Start the pipeline
            Thread t1 = new Thread((Runnable) inputReader);
            Thread t2 = new Thread((Runnable) circularShiftGenerator);
            Thread t3 = new Thread((Runnable) sorter);
            Thread t4 = new Thread((Runnable) outputFormatter);

            t1.start();
            t2.start();
            t3.start();
            t4.start();

            // Wait for all threads to complete
            t1.join();
            t2.join();
            t3.join();
            t4.join();

        } catch (Exception e) { e.printStackTrace(); }
    }
}