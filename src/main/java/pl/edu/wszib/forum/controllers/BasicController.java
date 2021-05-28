package pl.edu.wszib.forum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

    // Login form
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

/*    @GetMapping("/")
    public String homePage(Model model) {
        return "redirect:/index";
    }*/

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
