package atm;

import banknote.Banknote;

import java.io.Serializable;

public interface Cassette extends Serializable {
    int getMaxCountBanknotes();

    int getCountBanknotes();

    void addBanknotes(int countAddBanknotes);

    int addBanknotesToMax(int countAddBanknotes);

    void removeBanknotes(int countRemoveBanknotes);

    int removeBanknotesToMin(int countRemoveBanknotes);

    int getBalance();

    Banknote getUsedBanknote();
}
