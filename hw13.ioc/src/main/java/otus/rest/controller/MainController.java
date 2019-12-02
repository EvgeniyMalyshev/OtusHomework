package otus.rest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import otus.service.UserService;

@Controller
@Slf4j
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/admin_create")
    public ModelAndView logged(@RequestParam("login") String login, @RequestParam("password") String password) {

        if (login.equals("1") && password.equals("1")) {
            return new ModelAndView("admin_create");
        }
        return new ModelAndView("admin");
    }

    @PutMapping("/admin_create")
    public ModelAndView createUser(@RequestParam("user") String userName) {
        //userService.setUsers(userName);
        return new ModelAndView("admin_create");
    }

    @PostMapping("/admin_get_users")
    public ModelAndView userList(@RequestParam("getUsers") String string) {
        // userService.getUsers();
        return new ModelAndView(("admin_get_users"));
    }
}
