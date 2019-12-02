package otus.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otus.repository.UserRepository;
import otus.service.UserService;

@Configuration
public class SpringConfiguration {

    private UserRepository userRepository;

    @Bean
    public UserService userService() {
        return new UserService(userRepository);
    }
}
