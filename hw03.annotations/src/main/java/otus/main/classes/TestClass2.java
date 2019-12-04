package otus.main.classes;

import annotation.Test;
import annotations.After;
import annotations.Before;



public class TestClass2 {

    @Before
    public void BeforeTest(){
        System.out.println("Before otus.main.test class 2");
    }

    @Test
    public void Test(){
        System.out.println("Test run class 2");
    }

    @Test
    public void  WrongTest(){
        System.out.println("Something wrong in class 2!");
        throw new RuntimeException("Test class 1 is falling");
    }

    @After
    public void AfterTest(){
        System.out.println("After otus.main.test class 2");
    }
}
