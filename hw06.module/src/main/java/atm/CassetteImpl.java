package atm;

public class CassetteImpl implements Cassette {

    private Banknote banknote;
    private int countBanknote = 0;

    public CassetteImpl(Banknote banknote, int countBanknote) {
        this.banknote = banknote;
        this.countBanknote = countBanknote;
    }

    @Override
    public int getNominal() {
        return banknote.getNominal();
    }

    @Override
    public int getCountNote() {
        return countBanknote;
    }

    @Override
    public void addNote(int countAddBanknote) {
        countBanknote += countAddBanknote;
    }

    @Override
    public void removeNote(int countRemoveBanknote) {
        countBanknote -= countRemoveBanknote;
    }

    @Override
    public int getBalance() {
        return banknote.getNominal() * countBanknote;
    }

}
