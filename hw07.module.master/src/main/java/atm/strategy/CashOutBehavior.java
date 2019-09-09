package atm.strategy;


import atm.Cassette;
import banknote.BundleOfBanknotes;
import banknote.enums.Currency;

import java.util.List;

public interface CashOutBehavior {

    BundleOfBanknotes getBundleToCashOut(Currency currency, int summ, List<Cassette> cassettes);

}
