

public class Thread1 implements Runnable {

    private final Count count;
    private final int rounds;

    Thread1(Count count, int rounds) {
        this.count = count;
        this.rounds = rounds;
    }

    public void run() {
        for (int i = 0; i < rounds; i++) {
            count.put();
        }
    }
}
