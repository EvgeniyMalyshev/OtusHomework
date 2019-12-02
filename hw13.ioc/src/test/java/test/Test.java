
package test;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import otus.hibernate.DbConfiguration;
import otus.service.UserService;

//import otus.repository.UserRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DbConfiguration.class)
@TestPropertySource("classpath:hibernate.properties")

public class Test {


    @Autowired
    UserService userService;

    @org.junit.Test
    public void repositoryTest(){

        userService.setUsers("user");
        Assert.assertNotNull(userService.getUsers());

    }

}

