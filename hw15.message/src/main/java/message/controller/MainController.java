package message.controller;


import message.data.User;
import message.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String login() {
        userService.setUsersWithPass("user", "user");
        return "login";
    }

    @PostMapping(value = "/admin_create")
    public ModelAndView logged(@RequestParam("login") String login, @RequestParam("password") String password) {
        List<User> userList = userService.getUsers();
        if (!userList.isEmpty()) {
            if (login.equals(userList.get(0).getName()) && password.equals(userList.get(0).getPassword())) {
                return new ModelAndView("admin_create");
            }
        }
        return new ModelAndView("admin");
    }

    @PostMapping(value = "/create_user")
    public String createUser(@RequestParam("user") String userName) {
        userService.setUser(userName);
        return "admin_create";
    }

    @GetMapping("/admin_get_users")
    public ModelAndView userList() {
        return new ModelAndView("admin_create", "list", userService.getUsers());
    }
}
