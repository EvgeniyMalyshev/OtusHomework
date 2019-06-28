package com.otus.homework.hw02.list;
import java.util.Collections;
import java.util.List;


public class Util {
     static void setMath(List<Integer> mathList, int numbers) {
        if (numbers<20){
            numbers=20;
        }
        for(int i = 0; i < numbers; i++ ){
            mathList.add(i);
        }
        Collections.shuffle(mathList);
    }

     static void setString(List<String> list, String word) {
        while (list.size()<20){
            list.add(word + " ");
        }
    }
}
