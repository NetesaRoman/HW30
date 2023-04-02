package com.example.hw30.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


/*
 *
 * @author Roman Netesa
 *
 */
@Controller
public class L3Controller {




    @GetMapping("/l3")
    public String l3() {

        return "l3";
    }


}
