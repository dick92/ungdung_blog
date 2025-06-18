package com.example.ung_dung_blog.controller;

import com.example.ung_dung_blog.model.Blog;
import com.example.ung_dung_blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("blogs", blogService.findAll());
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "error-404";
        }
        model.addAttribute("blog", blog);
        return "view";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "error-404";
        }
        model.addAttribute("blog", blog);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return "re  direct:/blogs";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return "error-404";
        }
        blogService.delete(id);
        return "redirect:/blogs";
    }
}
