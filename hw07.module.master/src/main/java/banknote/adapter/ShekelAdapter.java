package banknote.adapter;

import banknote.Banknote;
import banknote.SimpleEqBanknotesImpl;
import banknote.Shekel;
import banknote.enums.Currency;
import banknote.enums.Nominal;

public class ShekelAdapter implements Banknote {

    private Shekel shekel;
    private Nominal nominal;

    public ShekelAdapter(Shekel shekel){
        this.shekel = shekel;
        for(Nominal nom: Nominal.values()){
            if(nom.getValue() == shekel.getNominal()){
                nominal = nom;
                break;
            }
        }
        if(nominal == null){
            throw new IllegalArgumentException("такого номинала нет");
        }
    }

    @Override
    public Nominal getNominal() {
        return nominal;
    }

    @Override
    public Currency getCurrency() {
        return Currency.NIS;
    }

    @Override
    public boolean equals(Object o){
        if(o == null || getClass() != o.getClass()) return false;
        return new SimpleEqBanknotesImpl().equal(this, (Banknote) o);
    }

    @Override
    public int hashCode(){
        return this.getNominal().getValue() + this.getCurrency().hashCode();
    }
}
