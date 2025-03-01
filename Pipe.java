import java.util.ArrayList;
import java.util.List;

// Pipe class to connect filters
class Pipe {
    // Buffer to store lines
    private List<Line> buffer = new ArrayList<>();
    // Flag to indicate if the pipe is closed for writing
    private boolean closed = false;

    // Method to add a line to the buffer
    public synchronized void putLine(Line line) throws InterruptedException {
        int limit = 100; // Buffer size limit

        // Wait if the buffer is full
        while (buffer.size() >= limit) { wait(); }

        // Add the line to the buffer
        buffer.add(line);

        // Notify all waiting threads
        notifyAll();
    }

    // Method to get a line from the buffer
    public synchronized Line getLine() throws InterruptedException {
        // Wait if the buffer is empty and the pipe is not closed
        while (buffer.isEmpty() && !closed) { wait(); }

        // Return null if the buffer is empty and the pipe is closed
        if (buffer.isEmpty() && closed) { return null; }
        
        // Remove and return the first line from the buffer
        Line line = buffer.remove(0);

        // Notify all waiting threads
        notifyAll();

        return line;
    }

    // Method to close the pipe for writing
    public synchronized void closeForWriting() {
        closed = true; // Set the closed flag to true
        notifyAll();   // Notify all waiting threads
    }
}
