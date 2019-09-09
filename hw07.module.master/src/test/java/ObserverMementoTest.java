import atm.AbstractATM;
import banknote.BanknoteImpl;
import banknote.enums.Currency;
import banknote.enums.Nominal;
import org.junit.Assert;
import org.junit.Test;


public class ObserverMementoTest extends ParentTest {

    @Test
    public void checkObserverWithMemento() {
        DepartmentOfATM department = new DepartmentOfATM();
        AbstractATM atm1 = getDefaultATM();
        AbstractATM atm2 = getDefaultATM();
        AbstractATM atm3 = getDefaultATM();
        department.addATM(atm1);
        department.addATM(atm2);
        department.addATM(atm3);
        Assert.assertNotNull(department);

        System.out.println("Баланс рублей в atm до операций = " + atm1.getBalance(Currency.RUB));
        atm1.cashIn(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 20);
        Assert.assertEquals(2500, atm1.getBalance(Currency.RUB));
        System.out.println("Баланс рублей в atm после взноса = " + atm1.getBalance(Currency.RUB));
        atm1.cashOut(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 600);
        Assert.assertEquals(2000, atm1.getBalance(Currency.RUB));
        System.out.println("Баланс рублей в atm после выдачи = " + atm1.getBalance(Currency.RUB));
        atm1.cashOut(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК), 100);
        Assert.assertEquals(1900, atm1.getBalance(Currency.RUB));

        department.returnToStartState();
        System.out.println("Баланс рублей в atm после сброса = " + atm1.getBalance(Currency.RUB));
        Assert.assertEquals(1500, atm1.getBalance(Currency.RUB));

        /*Сделано коряво, но сейчас слишком много придется перерабатывать для удобства тестирования.
        на будущее учту*/
        System.out.println(department.getBalanceSummary());

    }
}
