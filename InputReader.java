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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
