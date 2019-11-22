class Count {
    private int counter = 0;
    private int number = 0;

    synchronized void putGet(String state) {
        if (state.equals("put")) {
            put();
        } else {
            get();
        }

    }

    private void put() {
        while (counter > number) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("line1" + " " + counter);
        counter++;
        notify();

    }

    private void get() {
        while (counter < number) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("line2" + " " + number);
        number++;
        notify();
    }

}
