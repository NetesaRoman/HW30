package com.example.hw30.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

/*
 *
 * @author Roman Netesa
 *
 */
@Controller
public class MainController {

    @Value("${info.title}")
    private String infoTitle;
    @Value("${welcome.title}")
    private String welcomeTitle;

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("title", welcomeTitle);
        model.addAttribute(
                "year",
                "©" + LocalDate.now().getYear() + "Моя компания. Все права защищены."
        );
        model.addAttribute("mainText", "Some text for work space");
        return "welcome";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("title", infoTitle);
        model.addAttribute(
                "year",
                "©" + LocalDate.now().getYear() + "Моя компания. Все права защищены.");
        model.addAttribute("mainText", java.lang.System.currentTimeMillis());
        return "info";
    }
}
