package hib.service;

import hib.data.User;
import hib.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void setUsersWithPass(String name, String password) {
        User user = new User(name, password);
        userRepository.save(user);
    }

    public User setUser(String name) {
        User user = new User(name);
        userRepository.save(user);
        return user;
    }

}
