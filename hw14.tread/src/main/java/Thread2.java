
public class Thread2 implements Runnable {

    private final Count count;
    private final int rounds;

    Thread2(Count count, int rounds) {
        this.count = count;
        this.rounds = rounds;
    }

    public void run() {
        for (int i = 0; i < rounds; i++) {
            count.get();
        }
    }
}
