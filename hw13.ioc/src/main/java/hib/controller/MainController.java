package hib.controller;

import hib.data.User;
import hib.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/admin_create", method = POST)
    public ModelAndView logged(@RequestParam("login") String login, @RequestParam("password") String password) {

        if (login.equals("user") && password.equals("user")) {
            return new ModelAndView("admin_create");
        }
        return new ModelAndView("admin");
    }

    @RequestMapping("/create_user")
    public ModelAndView createUser(@RequestParam("user") String userName) {
        userService.setUser(userName);
        ModelAndView modelAndView = new ModelAndView("create_user");
        //modelAndView.addObject("msg", "User with name: " + userName);
        return modelAndView;
    }

    @RequestMapping("/admin_get_users")
    public ModelAndView userList() {
        List<User> userList = userService.getUsers();
        return new ModelAndView("admin_get_users");
    }
}
