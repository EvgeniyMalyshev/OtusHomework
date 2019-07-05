package test.classes;

import annotations.After;
import annotations.Before;
import annotations.Test;


public class TestClass3 {

    @Before
    public void BeforeTest(){
        System.out.println("Before test class 3");
    }

    @Test
    public void Test(){
        System.out.println("Test run class 3");
    }

    @Test
    public void  WrongTest(){
        System.out.println("Something wrong in class 3!");
        throw new RuntimeException("Test class 3 is falling");
    }

    @After
    public void AfterTest(){
        System.out.println("After test class 3");
    }
}
