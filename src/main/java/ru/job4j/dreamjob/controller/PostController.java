package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;
import ru.job4j.dreamjob.utility.Utility;

import javax.servlet.http.HttpSession;

@Controller
@ThreadSafe
public class PostController {

    private final PostService postService;
    private final CityService cityService;

    public PostController(PostService postService, CityService cityService) {
        this.postService = postService;
        this.cityService = cityService;
    }

    @GetMapping("/posts")
    public String posts(Model model, HttpSession session) {
        model.addAttribute("posts", postService.findAll());
        User user = Utility.logUser(model, session);
        model.addAttribute("user", user);
        return "posts";
    }

    @GetMapping("/formAddPost")
    public String addPost(Model model, HttpSession session) {
        model.addAttribute("post", new Post(0, "Заполните поле", false, new City()));
        model.addAttribute("cities", cityService.getAllCities());
        User user = Utility.logUser(model, session);
        model.addAttribute("user", user);
        return "addPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post, @RequestParam(name = "city.id") int id) {
        postService.add(post);
        return "redirect:/posts";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id) {
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("cities", cityService.getAllCities());
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post, @RequestParam(name = "city.id") int id) {
        City city = cityService.findById(id);
        post.setCity(city);
        postService.update(post);
        return "redirect:/posts";
    }

}
