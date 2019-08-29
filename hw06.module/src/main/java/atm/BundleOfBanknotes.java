package atm;

import java.util.ArrayList;
import java.util.List;


class BundleOfBanknotes {

    private final List<Banknote> banknotes = new ArrayList<>();

    List<Banknote> getBanknotes() {
        return banknotes;
    }

    void addBanknote(Banknote banknote) {
        banknotes.add(banknote);
    }

}
