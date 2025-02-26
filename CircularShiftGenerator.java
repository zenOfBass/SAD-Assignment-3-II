import java.util.List;

class CircularShiftGenerator implements Filter {
    private Pipe input;
    private Pipe output;
    
    public CircularShiftGenerator(Pipe input, Pipe output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        try {
            Line line;
            while ((line = input.getLine()) != null) {
                List<Line> shifts = line.generateCircularShifts();
                for (Line shift : shifts) {
                    output.putLine(shift);
                }
            }
            output.closeForWriting();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
