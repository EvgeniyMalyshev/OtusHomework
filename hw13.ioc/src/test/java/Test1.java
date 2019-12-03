import hib.configurations.SpringConfiguration;
import hib.data.User;
import hib.repository.UserRepository;
import hib.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
@TestPropertySource("classpath:hibernate.properties")
@WebAppConfiguration

public class Test1 {


    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    @Commit
    public void repositoryFill() {
        User u = new User("user", "user");
        userRepository.save(u);
        Assert.assertNotNull(userRepository.findAll());
    }


    @Test
    @Rollback
    public void serviceTest() {
        userService.setUsersWithPass("user", "password");
        Assert.assertNotNull(userService.getUsers());

    }

}


