package main.classes;

import annotations.After;
import annotations.Before;
import annotations.Test;


public class TestClass2 {

    @Before
    public void BeforeTest(){
        System.out.println("Before main.test class 2");
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
        System.out.println("After main.test class 2");
    }
}
