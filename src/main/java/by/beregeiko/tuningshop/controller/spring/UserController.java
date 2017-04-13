package by.beregeiko.tuningshop.controller.spring;

import by.beregeiko.tuningshop.entity.User;
import by.beregeiko.tuningshop.service.SecurityService;
import by.beregeiko.tuningshop.service.UserService;
import by.beregeiko.tuningshop.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Think on 06.04.2017.
 */
@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String list(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "users/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm,
                               BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        logger.info("Registraition detected");

        if(bindingResult.hasErrors()) {
            logger.info("BindingResult error detected");
            return "users/registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            logger.info("Login error detected");
            model.addAttribute("error", "Login or password is incorrect.");
        }
        if (logout != null) {
            logger.info("Logout detected");
            model.addAttribute("message", "Logged out successfully.");
        }
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin/admin";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model){
        logger.info("Listing users");
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users/list";
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public String showUser(@PathVariable("userId") int userId, Model model){
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "users/userDetails";
    }

    @RequestMapping(value = "/users/{userId}/delete", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userId") Integer userId, Model model){
        logger.info("Deleting user");
        userService.delete(userService.findUserById(userId));
        return "admin/admin";
    }

    // TODO: add methods: createAdminUser, updateUser

}
