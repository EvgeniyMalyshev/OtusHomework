import atm.ATM;
import atm.Banknote;
import atm.CassetteImpl;
import org.junit.Assert;
import org.junit.Test;

public class AtTest {

    @Test
    public void test(){
        ATM realATM = new ATM();

        realATM.loadCassette(new CassetteImpl(new Banknote(5000), 10));
        Assert.assertNotNull(realATM);
        Assert.assertEquals(50000, realATM.getBalanceAtm());
        realATM.loadCassette(new CassetteImpl(new Banknote(100), 100));
        Assert.assertNotNull(realATM);
        Assert.assertEquals(60000, realATM.getBalanceAtm());
        realATM.loadCassette(new CassetteImpl(new Banknote(500), 50));
        Assert.assertNotNull(realATM);
        Assert.assertEquals(85000, realATM.getBalanceAtm());
        realATM.loadCassette(new CassetteImpl(new Banknote(1000), 2));
        Assert.assertNotNull(realATM);
        Assert.assertEquals(87000, realATM.getBalanceAtm());
        realATM.loadCassette(new CassetteImpl(new Banknote(1000), 1));
        Assert.assertEquals(88000, realATM.getBalanceAtm());


        realATM.cashIn(500, 3);
        Assert.assertEquals(89500, realATM.getBalanceAtm());
        System.out.println(realATM.getDetailBalance());

        realATM.cashIn(500, -3);
        Assert.assertEquals(89500, realATM.getBalanceAtm());
        System.out.println(realATM.getDetailBalance());


        realATM.cashOut(1000);
        Assert.assertEquals(88500, realATM.getBalanceAtm());
        System.out.println(realATM.getDetailBalance());

        realATM.cashOut(100500);
        Assert.assertEquals(88500,realATM.getBalanceAtm());
        System.out.println(realATM.getDetailBalance());
    }
}
