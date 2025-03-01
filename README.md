# KWIC System

This project uses a pipe and filter architecture to implement a Key Word in Context (KWIC) system. The KWIC system processes a given text input to generate circular shifts of each line, sorts these shifts, and formats the output.

## Project Structure

- `CircularShiftGenerator.java`: Generates circular shifts for each line of text.
- `Filter.java`: Abstract interface for all filters.
- `InputReader.java`: Reads input text and sends lines to the next filter.
- `KWICSystem.java`: Main class to run the KWIC system.
- `Line.java`: Represents a line of text and provides methods to generate circular shifts and compare lines.
- `OutputFormatter.java`: Formats and prints the sorted lines.
- `Pipe.java`: Connects filters and provides methods to transfer lines between filters.
- `Sorter.java`: Sorts the circularly shifted lines.

## Documentation

- `README.md`: *Hello world!*
- `Kwic_class.puml`: UML diagram for the KWIC system in Plant Text form.
- `Uml.png`: Image of the UML diagram.

## Components

### Filter

The `Filter` interface defines the behavior for all filters in the system. Each filter implements the `Runnable` interface to support concurrent execution.

### Pipe

The `Pipe` class is a communication channel between filters, implementing a thread-safe buffer with synchronization mechanisms to handle concurrent access.

### Line

The `Line` class represents a line of text and provides:
- Methods to generate circular shifts
- Implementation of the `Comparable` interface for sorting
- Utility methods for text processing

### The Filters

1. **InputReader**: Parses input text and transforms it into `Line` objects
2. **CircularShiftGenerator**: Creates all possible circular shifts of each line
3. **Sorter**: Sorts all shifts alphabetically according to the specified ordering rules
4. **OutputFormatter**: Formats the sorted shifts for display

## How It Works

1. Input text is processed by the `InputReader` and converted to `Line` objects
2. Each `Line` is sent through a `Pipe` to the `CircularShiftGenerator`
3. The `CircularShiftGenerator` creates all circular shifts and sends them to the `Sorter`
4. The `Sorter` collects all shifts, and sorts them according to the rule a < A < b < B < ... < y < Y < z < Z
5. The `OutputFormatter` receives the sorted shifts and formats them for output

## Concurrency

The system utilizes Java threads to process data concurrently:
- Each filter runs in its thread
- Thread synchronization is handled through the `Pipe` implementation
- Flow control prevents buffer overflow through wait/notify mechanisms
