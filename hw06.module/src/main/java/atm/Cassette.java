package atm;

public class Cassette {

    private Banknote banknote;
    private int countBanknote = 0;

    public Cassette(Banknote banknote, int countBanknote) {
        this.banknote = banknote;
        this.countBanknote = countBanknote;
    }

    int getNominal() {
        return banknote.getNominal();
    }

    int getCountNote() {
        return countBanknote;
    }

    void addNote(int countAddBanknote) {
        countBanknote += countAddBanknote;
    }

    void removeNote(int countRemoveBanknote) {
        countBanknote -= countRemoveBanknote;
    }

    int getBalance() {
        return banknote.getNominal() * countBanknote;
    }

}
