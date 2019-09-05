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

        System.out.println("Баланс рублей в atm до операций = " + atm1.getBalance(Currency.RUB));
        atm1.cashIn(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 20);
        System.out.println("Баланс рублей в atm после взноса = " + atm1.getBalance(Currency.RUB));
        atm1.cashOut(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 600);
        System.out.println("Баланс рублей в atm после выдачи = " + atm1.getBalance(Currency.RUB));
        atm1.cashOut(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 100);

        department.returnToStartState();
        System.out.println("Баланс рублей в atm после сброса = " + atm1.getBalance(Currency.RUB));

        System.out.println(department.getBalanceSummary());

    }

    private ATMImpl getDefaultATM(){

        Cassette cassetteRub = new CassetteImpl(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК));
        cassetteRub.addBanknotes(30);

        Cassette cassetteRubSecond = new CassetteImpl(new BanknoteImpl(Currency.RUB, Nominal.СОТНЯ));

        Cassette cassetteEmpire = new CassetteImpl(new BanknoteImpl(Currency.NIS, Nominal.СОТНЯ));
        cassetteEmpire.addBanknotes(6);

        Cassette cassetteUsd = new CassetteImpl(new BanknoteImpl(Currency.USD, Nominal.ПОЛТИННИК));
        cassetteUsd.addBanknotes(16);

        ATMImpl atm = new ATMImpl();
        atm.on();
        atm.setCashOutBehavior(new MinBanknoteBehavior());
        atm.loadCassette(cassetteRub);
        atm.loadCassette(cassetteRubSecond);
        atm.loadCassette(cassetteEmpire);
        atm.loadCassette(cassetteUsd);

        return atm;
    }



}
