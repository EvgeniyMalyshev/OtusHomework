package banknote.enums;

public enum Currency {

    RUB("Рубль"),
    USD("Доллар США"),
    NIS("Новый израильский шекель");

    private String name;

    Currency(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
