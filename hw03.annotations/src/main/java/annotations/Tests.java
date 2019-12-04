package annotations;


import annotation.Test;
import org.springframework.stereotype.Service;

@Service
public class Tests {

    @Before
    public void before(){
        System.out.println("@Before logic");
    }

    @Test
    public void passedTest(){
        System.out.println("otus.main otus.main.test passed");
    }

    @Test
    public void faildTest(){
        System.out.println("otus.main otus.main.test fail");
        throw new RuntimeException("Runtime exception");
    }

    @After
    public void after(){
        System.out.println("@After logic");
    }
}
