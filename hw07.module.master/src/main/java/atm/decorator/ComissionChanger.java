package atm.decorator;


public abstract class ComissionChanger implements ChangerSummOperation {

    protected ChangerSummOperation changer;

    public ComissionChanger(ChangerSummOperation changer){
        this.changer = changer;
    }

}
