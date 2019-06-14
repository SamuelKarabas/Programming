package sk.karabas.myCode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Additional attributes for Thymeleaf
 */
@Controller
public class Attributes {

    @ModelAttribute("author")
    public String author() {
        return "Samuel Karabas";
    }

    @ModelAttribute("title")
    public String title() {
        return "Cassovia Code";
    }
}
