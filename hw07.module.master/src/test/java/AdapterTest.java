import banknote.BanknoteImpl;
import banknote.BundleOfBanknotes;
import banknote.Shekel;
import banknote.adapter.ShekelAdapter;
import banknote.enums.Currency;
import banknote.enums.Nominal;
import org.junit.Assert;
import org.junit.Test;


public class AdapterTest {

    @Test
    public void checkAdapterTest() {
        BundleOfBanknotes bundle = new BundleOfBanknotes();
        bundle.addBanknotes(new BanknoteImpl(Currency.USD, Nominal.ПОЛТИННИК), 11);
        Assert.assertEquals(11, bundle.getCountBanknotes());
        System.out.println(bundle);
        bundle.addBanknotes(new BanknoteImpl(Currency.USD, Nominal.ПОЛТИННИК), 15);
        Assert.assertEquals(26, bundle.getCountBanknotes());
        System.out.println(bundle);
        bundle.addBanknotes(new ShekelAdapter(new Shekel(100)), 5);
        Assert.assertEquals(31, bundle.getCountBanknotes());
        System.out.println(bundle);
    }
}
