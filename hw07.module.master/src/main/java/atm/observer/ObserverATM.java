package atm.observer;

public interface ObserverATM {

    void addListener(Listener listener);
    void notify(Directives directive);

}
