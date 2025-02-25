// InputReader filter
class InputReader implements Filter {
    private Pipe output;
    private String inputText;
    
    public InputReader(Pipe input, Pipe output) {
        this.output = output;
    }

    public void readLines(String text) {
        this.inputText = text;
    }

    @Override
    public void run() {
        try {
            String[] lines = inputText.split("\n");
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    output.putLine(new Line(line.trim()));
                }
            }
            output.closeForWriting();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
