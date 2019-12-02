package otus.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otus.service.UserService;

@Configuration
public class SpringConfiguration {

    @Bean
    public UserService userService() {
        return new UserService();
    }
}
