package atm;

import java.util.List;


public interface CashOutBehavior {

    BundleOfBanknotes getBundleToCashOut(List<Cassette> cassettes, int summ);

}
