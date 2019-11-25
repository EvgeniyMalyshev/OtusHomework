

public class Thread1 implements Runnable {

    private final Count count;
    private final int rounds;
    private final String state;

    Thread1(Count count, int rounds, String state) {
        this.count = count;
        this.rounds = rounds;
        this.state = state;
    }

    public void run() {
        for (int i = 0; i < rounds; i++) {
            count.putGet(state);
        }
    }
}
