package jannkasper.omdb.controllers;

import jannkasper.omdb.services.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ServerWebExchange;

@Controller
public class MovieController {

    private ApiService apiService;

    public MovieController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/{text}")
    public String formPost(Model model, ServerWebExchange serverWebExchange, @PathVariable String text){


        System.out.println(apiService.getMovie(text).toString());
        model.addAttribute("users", apiService.getMovie(text));

        return "userlist";
    }
}