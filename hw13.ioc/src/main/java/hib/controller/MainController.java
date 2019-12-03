package hib.controller;

import hib.data.User;
import hib.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Slf4j
public class MainController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public ModelAndView login() {
        userService.setUsersWithPass("user", "user");
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/admin_create", method = POST)
    public ModelAndView logged(@RequestParam("login") String login, @RequestParam("password") String password) {
        List<User> userList = userService.getUsers();
        if (login.equals(userList.get(0).getName()) && password.equals(userList.get(0).getPassword())) {
            return new ModelAndView("admin_create");
        }
        return new ModelAndView("admin");
    }

    @RequestMapping(value = "/create_user", method = POST)
    public String createUser(@RequestParam("user") String userName) {
        userService.setUser(userName);
        return "admin_create";
    }

    @GetMapping("/admin_get_users")
    public String userList(Model model) {
        List<User> userList = userService.getUsers();
        model.addAttribute("list", userList);
        return "admin_get_users";
    }
}