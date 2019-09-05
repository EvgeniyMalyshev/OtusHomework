import atm.AbstractATM;
import atm.commands.Command;
import atm.commands.OffAllAtmCommand;
import atm.commands.OnAllAtmCommand;
import atm.observer.Directives;
import atm.observer.ObserverATM;
import atm.observer.ObserverATMImpl;
import banknote.enums.Currency;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class DepartmentOfATM {

    private Set<AbstractATM> atms;
    private Command offCmd;
    private Command onCmd;
    private ObserverATM observerATM;

    DepartmentOfATM(){
        atms = new HashSet<>();
        offCmd = new OffAllAtmCommand(atms);
        onCmd = new OnAllAtmCommand(atms);
        observerATM = new ObserverATMImpl();
    }

    void addATM(AbstractATM atm){
        atms.add(atm);
        observerATM.addListener(atm);
    }

    String getBalanceSummary(){
        String message = "Общий баланс банкоматов\n";
        Map<Currency, Integer> mapBalances = new HashMap<>();
        for(AbstractATM atm : atms){
            Map<Currency, Integer> atmBalance = atm.getBalance();
            atmBalance.forEach((key, value) -> mapBalances.put(
                    key,
                    mapBalances.getOrDefault(key, 0) + value));
        }
        for(Map.Entry<Currency, Integer> element: mapBalances.entrySet()){
            message = message + "Валюта: " + element.getKey().getName() + " сумма: " + element.getValue() + "\n";
        }
        return message;
    }

    void offAllAtm(){
        offCmd.execute();
    }

    void onAllAtm(){
        onCmd.execute();
    }

    void returnToStartState() {
        observerATM.notify(Directives.RESET);
    }
}
