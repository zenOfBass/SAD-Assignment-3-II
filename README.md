# KWIC System

This project implements a Key Word in Context (KWIC) system using a pipe and filter architecture. The KWIC system processes a given text input to generate circular shifts of each line, sorts these shifts, and formats the output.

## Project Structure

- `CircularShiftGenerator.java`: Generates circular shifts for each line of text.
- `Filter.java`: Abstract interface for all filters.
- `InputReader.java`: Reads input text and sends lines to the next filter.
- `Kwic_class.puml`: UML diagram for the KWIC system.
- `KWICSystem.java`: Main class to run the KWIC system.
- `Line.java`: Represents a line of text and provides methods to generate circular shifts and compare lines.
- `OutputFormatter.java`: Formats and prints the sorted lines.
- `Pipe.java`: Connects filters and provides methods to transfer lines between filters.
- `Sorter.java`: Sorts the circularly shifted lines.
- `README.md`: This file.
- `Uml.png`: Image of the UML diagram.

## How It Works

1. **InputReader**: Reads the input text and sends each line to the next filter.
2. **CircularShiftGenerator**: Generates all circular shifts for each line.
3. **Sorter**: Sorts all the circularly shifted lines.
4. **OutputFormatter**: Formats and prints the sorted lines.

## Usage

To run the KWIC system, execute the `KWICSystem` class. The main method initializes the pipes and filters, and starts the processing.

```java
public class KWICSystem {
    public static void main(String[] args) {
        try {
            // Initialize pipes
            Pipe pipe1 = new Pipe();
            Pipe pipe2 = new Pipe();
            Pipe pipe3 = new Pipe();
            Pipe pipe4 = new Pipe();

            // Initialize filters
            InputReader inputReader = new InputReader(pipe1, pipe2);
            CircularShiftGenerator circularShiftGenerator = new CircularShiftGenerator(pipe2, pipe3);
            Sorter sorter = new Sorter(pipe3, pipe4);
            OutputFormatter outputFormatter = new OutputFormatter(pipe4, null);

            // Start filters in separate threads
            Thread inputThread = new Thread(inputReader);
            Thread shiftThread = new Thread(circularShiftGenerator);
            Thread sortThread = new Thread(sorter);
            Thread outputThread = new Thread(outputFormatter);

            inputThread.start();
            shiftThread.start();
            sortThread.start();
            outputThread.start();

            // Wait for all threads to finish
            inputThread.join();
            shiftThread.join();
            sortThread.join();
            outputThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}