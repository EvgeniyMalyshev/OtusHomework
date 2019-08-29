import atm.ATM;
import atm.Banknote;
import atm.Cassette;

public class ATMain {

    public static void main(String[] args){
        ATM realATM = new ATM();
        realATM.loadCassette(new Cassette(new Banknote(5000), 10));
        realATM.loadCassette(new Cassette(new Banknote(100), 100));
        realATM.loadCassette(new Cassette(new Banknote(500), 50));
        realATM.loadCassette(new Cassette(new Banknote(1000), 2));
        realATM.loadCassette(new Cassette(new Banknote(1000), 1));
        System.out.println(realATM.getDetailBalance());

        realATM.cashIn(500, 3);
        System.out.println(realATM.getDetailBalance());
        realATM.cashOut(100500);
        System.out.println(realATM.getDetailBalance());
        realATM.cashOut(10508);
        System.out.println(realATM.getDetailBalance());

    }

}
