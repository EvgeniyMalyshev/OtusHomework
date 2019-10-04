import atm.AbstractATM;
import banknote.BanknoteImpl;
import banknote.enums.Currency;
import banknote.enums.Nominal;
import org.junit.Assert;
import org.junit.Test;


public class DecoratorTest extends ParentTest {


    @Test
    public void checkDecorator() {
        DepartmentOfATM department = new DepartmentOfATM();
        AbstractATM atm1 = getDefaultATM();
        department.addATM(atm1);
        Assert.assertNotNull(department);

        atm1.cashOut(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 200000);
        Assert.assertEquals(1500, atm1.getBalance(Currency.RUB));

        atm1.cashOut(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 5);
        Assert.assertEquals(1500, atm1.getBalance(Currency.RUB));

        atm1.cashOut(new BanknoteImpl(Currency.NIS, Nominal.СОТНЯ), 200);
        Assert.assertEquals(500, atm1.getBalance(Currency.NIS));
    }


}
