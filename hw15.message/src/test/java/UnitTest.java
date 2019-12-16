import message.AppStart;
import message.repository.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@Import(AppStart.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppStart.class)
public class UnitTest {
    @Autowired
    UserRepository userRepository;
}
