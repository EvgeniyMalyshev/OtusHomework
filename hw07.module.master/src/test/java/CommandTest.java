import atm.ATMImpl;
import atm.AbstractATM;
import atm.CassetteImpl;
import banknote.BanknoteImpl;
import banknote.enums.Currency;
import banknote.enums.Nominal;
import org.junit.Assert;
import org.junit.Test;

public class CommandTest {

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
}
