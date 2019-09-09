package atm;

public class CassetteImpl implements Cassette {

    private Banknote banknote;
    private int countBanknote;

    public CassetteImpl(Banknote banknote, int countBanknote) {
        this.banknote = banknote;
        if (countBanknote>0){
            this.countBanknote = countBanknote;
        } else {
            this.countBanknote = 0;
        }

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
        if (countAddBanknote>0){
            countBanknote += countAddBanknote;
        }
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
