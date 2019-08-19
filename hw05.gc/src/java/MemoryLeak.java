import java.util.ArrayList;
import java.util.List;

class MemoryLeak {

    void run() throws InterruptedException {
        List<String> list = new ArrayList<>();
        for(;;){
            StringBuilder str = new StringBuilder();
            for(int i = 0; i < 100; i ++){
                str.append(String.valueOf(System.currentTimeMillis()));
            }
            list.add(str + "&");
            list.add(str + "|" + str);
            list.add(str + "time to fall");
            list.remove(0);
            Thread.sleep(1);
        }
    }

}
