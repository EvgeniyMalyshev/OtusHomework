package annotations;


import org.springframework.stereotype.Service;

@Service
public class Tests {

    @Before
    public void before(){
        System.out.println("@Before logic");
    }

    @Test
    public void passedTest(){
        System.out.println("main main.test passed");
    }

    @Test
    public void faildTest(){
        System.out.println("main main.test fail");
        throw new RuntimeException("Runtime exception");
    }

    @After
    public void after(){
        System.out.println("@After logic");
    }
}
