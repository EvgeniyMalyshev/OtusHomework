import hib.configurations.SpringConfiguration;
import hib.data.User;
import hib.repository.UserRepository;
import hib.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
@TestPropertySource("classpath:config.properties")
@WebAppConfiguration

public class UnitTest {


    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    @Commit
    public void repositoryFill() {
        User u = new User("user", "user");
        userRepository.saveAndFlush(u);
        Assert.assertNotNull(userRepository.findAll());
    }


    @Test
    @Commit
    public void serviceTest() {
        userService.setUsersWithPass("user", "password");
        List list = userService.getUsers();
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());


    }

}


