import org.junit.Test;

public class GCTest {
    private GcStarter starter = new GcStarter();
    private MemoryLeak memoryLeak = new MemoryLeak();
    private Monitor monitor = new Monitor();

    @Test
    public void GcTest() {
        monitor.switchOnMonitoring();

        try {
            memoryLeak.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printStatistic() {
        starter.printStat("./logs/SGC_1.log");
        starter.printStat("./logs/PGC_1.log");
        starter.printStat("./logs/G1_1.log");
        starter.printStat("./logs/SGC_add.log");
        starter.printStat("./logs/PGC_add.log");
        starter.printStat("./logs/G1_add.log");
        starter.printStat("./logs/SGC_2.log");
        starter.printStat("./logs/PGC_2.log");
        starter.printStat("./logs/G1_2.log");
        starter.printStat("./logs/SGC_3.log");
        starter.printStat("./logs/PGC_3.log");
        starter.printStat("./logs/G1_3.log");
    }
}
