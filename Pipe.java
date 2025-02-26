import java.util.ArrayList;
import java.util.List;

// Pipe class to connect filters
class Pipe {
    private List<Line> buffer = new ArrayList<>();
    private boolean closed = false;

    public synchronized void putLine(Line line) throws InterruptedException {
        while (buffer.size() >= 100) { // Buffer size limit
            wait();
        }
        buffer.add(line);
        notifyAll();
    }

    public synchronized Line getLine() throws InterruptedException {
        while (buffer.isEmpty() && !closed) {
            wait();
        }

        if (buffer.isEmpty() && closed) {
            return null; // Signal end of input
        }

        Line line = buffer.remove(0);
        notifyAll();
        return line;
    }

    public synchronized void closeForWriting() {
        closed = true;
        notifyAll();
    }
}
