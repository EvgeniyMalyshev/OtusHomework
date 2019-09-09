import java.util.HashMap;
import java.util.Map;
import static constants.Constants.*;

 class StatisticCounter {

    private Map<String, Integer> statisticMap = new HashMap<>();
    private int calculatePassed = 0;
    private int calculateFailed = 0;

    void count(String description,Integer number){
        switch (description) {
            case PASSED:
                calculatePassed += number;
                statisticMap.put(description, calculatePassed);
                break;
            case FAILED:
                calculateFailed += number;
                statisticMap.put(description, calculateFailed);
                break;
            default:
                System.out.println("This data is not supported");
                break;
        }

    }

    public void print(){
        statisticMap.entrySet().forEach(entry->{
            System.out.println("All time " + entry.getKey() + " " + entry.getValue());
     });
        System.out.println("Number of all tests was " + (calculateFailed + calculatePassed));
    }
}


