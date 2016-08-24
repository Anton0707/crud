package net.usermanager.mvc.controller;

import net.usermanager.mvc.model.User;
import net.usermanager.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Oleg on 07.08.2016.
 */

@Controller
public class UserController {
    private UserService userService;
    public static Integer age = null;
    public static boolean isFiltered = false;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(Model model, Integer offset, Integer maxResults) {
        model.addAttribute("user", new User());
        if (!UserController.isFiltered) {
            UserController.age = null;
            model.addAttribute("count", this.userService.count());
        } else {
            model.addAttribute("count", this.userService.count(age));
        }
        model.addAttribute("listUsers", this.userService.listUsers(age, offset, maxResults));
        model.addAttribute("offset", offset);
        return "users";
    }

    @RequestMapping(value = "/filterusers", method = RequestMethod.POST)
    public String filterUsers(@RequestParam int age, ModelMap model, Integer offset, Integer maxResults) {
        UserController.age = age;
        UserController.isFiltered = true;
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.listUsers(UserController.age, offset, maxResults));
        model.addAttribute("count", this.userService.count(UserController.age));
        model.addAttribute("offset", offset);
        return "users";
    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public String deleteAge(ModelMap model, Integer offset, Integer maxResults) {
        UserController.age = null;
        UserController.isFiltered = false;
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.listUsers(UserController.age, offset, maxResults));
        model.addAttribute("count", this.userService.count());
        model.addAttribute("offset", offset);
        return "users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        Integer position = this.userService.getPositionById(id, UserController.age);
        this.userService.removeUser(id);
        int page;
        if (position % 10 == 1)
            page = ((int) ((Math.ceil((double) position / 10) - 1) * 10) - 10);
        else
            page = (int) ((Math.ceil((double) position / 10) - 1) * 10);
        return "redirect:/users?offset=" + page;
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model, Integer offset, Integer maxResults) {
        model.addAttribute("user", this.userService.getUserById(id));
        if (!UserController.isFiltered) {
            UserController.age = null;
            model.addAttribute("count", this.userService.count());
        } else {
            model.addAttribute("count", this.userService.count(UserController.age));
        }
        model.addAttribute("listUsers", this.userService.listUsers(UserController.age, offset, maxResults));
        model.addAttribute("offset", offset);
        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        Integer position = this.userService.getPositionById(user.getId(), UserController.age);
        String url = "redirect:/users?offset=";
        if (user.getId() == 0) {
            this.userService.addUser(user);
            if (UserController.isFiltered)
                url += (int) ((Math.ceil((double) this.userService.count(UserController.age) / 10) - 1) * 10);
            else
                url += (int) ((Math.ceil((double) this.userService.count() / 10) - 1) * 10);
        } else {
            this.userService.updateUser(user);
            url += (int) ((Math.ceil((double) position / 10) - 1) * 10);
        }
        return url;
    }
}
