package banknote.enums;

public enum Nominal {

    ПОЛТИННИК(50),
    СОТНЯ(100),
    ПЯТИСОТЕННАЯ(500),
    ТЫСЯЧНАЯ(1000),
    ПЯТИТЫСЯЧНАЯ(5000);

    private int nominal;

    Nominal(int nominal){
        this.nominal = nominal;
    }

    public int getValue(){
        return nominal;
    }

}
