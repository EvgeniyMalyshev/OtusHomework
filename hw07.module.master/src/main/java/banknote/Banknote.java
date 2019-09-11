package banknote;

import banknote.enums.Currency;
import banknote.enums.Nominal;

public interface Banknote {

     Nominal getNominal();
     Currency getCurrency();

}
