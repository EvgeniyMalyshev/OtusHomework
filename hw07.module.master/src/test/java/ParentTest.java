import atm.ATMImpl;
import atm.Cassette;
import atm.CassetteImpl;
import atm.strategy.MinBanknoteBehavior;
import banknote.BanknoteImpl;
import banknote.enums.Currency;
import banknote.enums.Nominal;

 class ParentTest {


    protected ATMImpl getDefaultATM() {

        Cassette cassetteRub = new CassetteImpl(new BanknoteImpl(Currency.RUB, Nominal.ПОЛТИННИК));
        cassetteRub.addBanknotes(30);

        Cassette cassetteRubSecond = new CassetteImpl(new BanknoteImpl(Currency.RUB, Nominal.СОТНЯ));

        Cassette cassetteSheckel = new CassetteImpl(new BanknoteImpl(Currency.NIS, Nominal.СОТНЯ));
        cassetteSheckel.addBanknotes(6);

        Cassette cassetteUsd = new CassetteImpl(new BanknoteImpl(Currency.USD, Nominal.ПОЛТИННИК));
        cassetteUsd.addBanknotes(16);

        ATMImpl atm = new ATMImpl();
        atm.on();
        atm.setCashOutBehavior(new MinBanknoteBehavior());
        atm.loadCassette(cassetteRub);
        atm.loadCassette(cassetteRubSecond);
        atm.loadCassette(cassetteSheckel);
        atm.loadCassette(cassetteUsd);

        return atm;
    }


}
