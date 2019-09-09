import atm.ATMImpl;
import atm.AbstractATM;
import atm.Cassette;
import atm.CassetteImpl;
import atm.strategy.MinBanknoteBehavior;
import banknote.BanknoteImpl;
import banknote.BundleOfBanknotes;
import banknote.Shekel;
import banknote.adapter.ShekelAdapter;
import banknote.enums.Currency;
import banknote.enums.Nominal;
import org.junit.Assert;
import org.junit.Test;

public class AtTest {

    @Test
    public void checkCommandTest() {
        AbstractATM firstAtm = new ATMImpl();
        AbstractATM secondAtm = new ATMImpl();

        DepartmentOfATM department = new DepartmentOfATM();
        department.addATM(firstAtm);
        department.addATM(secondAtm);
        Assert.assertNotNull(department);

        firstAtm.loadCassette(new CassetteImpl(new BanknoteImpl(Currency.USD, Nominal.СОТНЯ)));
        Assert.assertNotNull(firstAtm);
        secondAtm.loadCassette(new CassetteImpl(new BanknoteImpl(Currency.USD, Nominal.СОТНЯ)));
        Assert.assertNotNull(firstAtm);

        department.onAllAtm();
        secondAtm.loadCassette(new CassetteImpl(new BanknoteImpl(Currency.USD, Nominal.СОТНЯ)));

        department.offAllAtm();
        secondAtm.loadCassette(new CassetteImpl(new BanknoteImpl(Currency.USD, Nominal.СОТНЯ)));

    }

    @Test
    public void checkAdapterTest(){
        BundleOfBanknotes bundle = new BundleOfBanknotes();
        bundle.addBanknotes(new BanknoteImpl(Currency.USD, Nominal.ПОЛТИННИК), 11);
        Assert.assertEquals(11,bundle.getCountBanknotes());
        System.out.println(bundle);
        bundle.addBanknotes(new BanknoteImpl(Currency.USD, Nominal.ПОЛТИННИК), 15);
        Assert.assertEquals(26,bundle.getCountBanknotes());
        System.out.println(bundle);
        bundle.addBanknotes(new ShekelAdapter(new Shekel(100)), 5);
        Assert.assertEquals(31,bundle.getCountBanknotes());
        System.out.println(bundle);
    }

    @Test
    public void checkObserverWithMemento(){
        DepartmentOfATM department = new DepartmentOfATM();
        AbstractATM atm1 = getDefaultATM();
        AbstractATM atm2 = getDefaultATM();
        AbstractATM atm3 = getDefaultATM();
        department.addATM(atm1);
        department.addATM(atm2);
        department.addATM(atm3);
        Assert.assertNotNull(department);

        System.out.println("Баланс рублей в atm до операций = " + atm1.getBalance(Currency.RUB));
        Assert.assertEquals("{NIS=600, RUB=1500, USD=800}",atm1.getBalance().toString());
        atm1.cashIn(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 20);
        Assert.assertEquals("{NIS=600, RUB=2500, USD=800}",atm1.getBalance().toString());
        System.out.println("Баланс рублей в atm после взноса = " + atm1.getBalance(Currency.RUB));
        atm1.cashOut(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 600);
        Assert.assertEquals("{NIS=600, RUB=2000, USD=800}",atm1.getBalance().toString());
        System.out.println("Баланс рублей в atm после выдачи = " + atm1.getBalance(Currency.RUB));
        atm1.cashOut(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 100);
        Assert.assertEquals("{NIS=600, RUB=1900, USD=800}",atm1.getBalance().toString());

        department.returnToStartState();
        System.out.println("Баланс рублей в atm после сброса = " + atm1.getBalance(Currency.RUB));
        Assert.assertEquals("{NIS=600, RUB=1500, USD=800}",atm1.getBalance().toString());

        System.out.println(department.getBalanceSummary());
        Assert.assertEquals("{NIS=600, RUB=1500, USD=800}",atm1.getBalance().toString());
    }

    @Test
    public void checkDecorator(){
        DepartmentOfATM department = new DepartmentOfATM();
        AbstractATM atm1 = getDefaultATM();
        department.addATM(atm1);
        Assert.assertNotNull(department);

        atm1.cashOut(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 200000);
        Assert.assertEquals("{NIS=600, RUB=1500, USD=800}",atm1.getBalance().toString());

        atm1.cashOut(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 5);
        Assert.assertEquals("{NIS=600, RUB=1500, USD=800}",atm1.getBalance().toString());

        atm1.cashOut(new BanknoteImpl(Currency.NIS, Nominal.СОТНЯ), 200);
        Assert.assertEquals("{NIS=500, RUB=1500, USD=800}",atm1.getBalance().toString());
    }


    private ATMImpl getDefaultATM(){

        Cassette cassetteRub = new CassetteImpl(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК));
        cassetteRub.addBanknotes(30);

        Cassette cassetteRubSecond = new CassetteImpl(new BanknoteImpl(Currency.RUB, Nominal.СОТНЯ));

        Cassette cassetteSheckel = new CassetteImpl(new BanknoteImpl(Currency.NIS, Nominal.СОТНЯ));
        cassetteSheckel.addBanknotes(6);

        Cassette cassetteUsd = new CassetteImpl(new BanknoteImpl(Currency.USD, Nominal.ПОЛТИННИК));
        cassetteUsd.addBanknotes(16);

        ATMImpl atm = new ATMImpl();
        atm.on();
        atm.setCashOutBehavior(new MinBanknoteBehavior());
        atm.loadCassette(cassetteRub);
        atm.loadCassette(cassetteRubSecond);
        atm.loadCassette(cassetteSheckel);
        atm.loadCassette(cassetteUsd);

        return atm;
    }



}
