import main.DIYarrayList;


import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();
        List<String> list = new DIYarrayList();
        List <String> copyList = new ArrayList<>();
        List<Integer> mathList = new DIYarrayList<>();

        Util.setString(stringList, "a");
        Util.setString(copyList, "b");
        Util.setMath(mathList, 3);

        list.addAll(stringList);
        System.out.println("в лист добавились стринги А");
        list.forEach(System.out::print);
        System.out.println(" ");

        DIYarrayList.copy (list,copyList);
        System.out.println("все А были заменены на B");
        list.forEach(System.out::print);
        System.out.println(" ");

        System.out.println("неотсортированные числа");
        mathList.forEach(System.out::print);
        System.out.println(" ");

        System.out.println("сортированные числа");
        DIYarrayList.sort(mathList);
        mathList.forEach(System.out::print);
        System.out.println(" ");

        System.out.println("Бросаем исключение с обработкой");
        try{
            list.containsAll(list);
        }catch (UnsupportedOperationException e){
            System.out.println("E're we got an exception");
        }

        System.out.println("Бросаем исключение");
        list.containsAll(list);


}


}
