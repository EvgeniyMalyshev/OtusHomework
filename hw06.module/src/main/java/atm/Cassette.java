package atm;

public interface Cassette {
    int getNominal();

    int getCountNote();

    void addNote(int countAddBanknote);

    void removeNote(int countRemoveBanknote);

    int getBalance();
}
