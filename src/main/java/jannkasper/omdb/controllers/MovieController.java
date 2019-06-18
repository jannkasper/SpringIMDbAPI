package jannkasper.omdb.controllers;

import jannkasper.omdb.services.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;

@Controller
public class MovieController {

    private ApiService apiService;

    public MovieController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public String index(){
        return "index";
    }

    @PostMapping("/search")
    public String searchMovies (Model model, ServerWebExchange serverWebExchange){
        MultiValueMap<String, String> map = serverWebExchange.getFormData().block();

        String text = new String(map.get("text").get(0));


        model.addAttribute("listMovie", apiService.getList(text));
        return "movielist";
    }

    @RequestMapping("/movie/{imdbid}")
    public String movieDetails(Model model, @PathVariable String imdbid){


        System.out.println(imdbid);
        model.addAttribute("movie", apiService.getMovie(imdbid));

        return "moviedetail";
    }
}