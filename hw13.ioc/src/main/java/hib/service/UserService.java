package hib.service;

import hib.data.User;
import hib.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        userRepository.saveAndFlush(user);
    }

    public void setUser(String name) {
        User user = new User(name);
        userRepository.saveAndFlush(user);
    }

}
