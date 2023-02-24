package com.example.wordGame.controller;


import com.example.wordGame.WordGameApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExController {

    @GetMapping({"/"})
    public String hello(Model model) {
        model.addAttribute("word", WordGameApplication.randomWord);

        return "index";
    }

}


