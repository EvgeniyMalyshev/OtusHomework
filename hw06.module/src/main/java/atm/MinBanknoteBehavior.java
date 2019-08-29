package atm;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinBanknoteBehavior implements CashOutBehavior {

    @Override
    public BundleOfBanknotes getBundleToCashOut(List<Cassette> cassettes, int summ) {
        cassettes.sort(Comparator.comparing(Cassette::getNominal));
        Collections.reverse(cassettes);
        BundleOfBanknotes resultBundle = new BundleOfBanknotes();
        for (Cassette cassette : cassettes) {
            int countInCassette = cassette.getCountNote();
            while (countInCassette > 0 && cassette.getNominal() <= summ && summ > 0) {
                resultBundle.addBanknote(new Banknote(cassette.getNominal()));
                summ -= cassette.getNominal();
                countInCassette--;
            }
        }
        if (summ != 0) {
            resultBundle.getBanknotes().clear();
        }
        return resultBundle;
    }
}
