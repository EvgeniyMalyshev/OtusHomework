import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Main {
    private static final int ITERATIONS_COUNT = 11;

    public static void main(String[] args) throws InterruptedException {

        List<Integer> list = new ArrayList<>();
        for (int a = 0; a < ITERATIONS_COUNT; a++) {
            list.add(0, a);
            Thread t1 = new Thread(() -> {
                try {
                    for (int i = 0; i < 1; i++) {
                        synchronized (list) {
                            out.println("tread 1 " + list.get(0));
                        }

                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            });
            Thread t2 = new Thread(() -> {
                try {
                    for (int i = 0; i < 1; i++) {
                        synchronized (list) {
                            out.println("tread 2 " + list.get(0));
                        }
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            });
            t1.start();
            t2.start();

            t1.join();
            t2.join();


        }


    }


}

