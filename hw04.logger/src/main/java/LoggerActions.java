import annotation.Log;

public class LoggerActions implements LoggerInterface {

    @Log
    @Override
    public void calculation(int number) {
        System.out.println("Param that we get is " + number);
    }
}


