class OutputFormatter implements Filter {
    private Pipe input;
    
    public OutputFormatter(Pipe input, Pipe output) {
        this.input = input;
    }

    @Override
    public void run() {
        try {
            System.out.println("KWIC Index System Output:");
            System.out.println("-------------------------");

            Line line;
            int count = 1;
            while ((line = input.getLine()) != null) {
                System.out.println(count + ". " + line);
                count++;
            }

            System.out.println("-------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
