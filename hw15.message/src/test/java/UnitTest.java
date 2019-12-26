import message.AppStart;
import message.data.User;
import message.repository.UserRepository;
import message.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@Import(AppStart.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppStart.class)
public class UnitTest {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback
    public void userServiceTest() {
        userService.setUser("testName");
        List<User> users = userRepository.findByName("testName");
        Assert.assertNotNull(users);
        Assert.assertEquals("testName", users.get(0).getName());
    }
}
