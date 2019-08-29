public class Main {
    public static void main(String[] args) {
         GcStarter starter = new GcStarter();
         MemoryLeak memoryLeak = new MemoryLeak();
         Monitor monitor = new Monitor();

        monitor.switchOnMonitoring();

        try {
            memoryLeak.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
