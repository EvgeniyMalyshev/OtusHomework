public class StartTreadsUtil {

    public static void start(int countOfRounds) {

        Count count = new Count();
        Thread1 lineOne = new Thread1(count, countOfRounds + 1, "get");
        Thread2 lineTwo = new Thread2(count, countOfRounds + 1, "put");
        new Thread(lineOne).start();
        new Thread(lineTwo).start();
    }


}
