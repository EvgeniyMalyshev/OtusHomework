package atm.decorator;


abstract class ComissionChanger implements ChangerSummOperation {

    ChangerSummOperation changer;

    ComissionChanger(ChangerSummOperation changer){
        this.changer = changer;
    }

}
