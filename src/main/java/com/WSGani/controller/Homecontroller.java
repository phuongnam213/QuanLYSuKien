package com.WSGani.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homecontroller {
    @GetMapping("/")
    public String home(){
        return "SuKien/listSK";
    }

    @GetMapping("/api")
    public String homes(){
        return "home/home";
    }

    @GetMapping("/details/{id}")
    public String details(){
        return "SuKien/detailsSK";
    }

    @GetMapping("/add")
    public String add(){
        return "SuKien/addSK";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin/admin";
    }

    @GetMapping("/admin/")
    public String homead(){
        return "admin/listSK";
    }

    @GetMapping("/admin/details/{id}")
    public String detailsad(){
        return "admin/detailsSK";
    }

    @GetMapping("/admin/add")
    public String addad(){
        return "admin/addSK";
    }

    @GetMapping("/acx")
    public String acx(){
        return "home/acx";
    }

    @GetMapping("/admin/profile")
    public String profile(){
        return "Users/profile";
    }

    @GetMapping("/admin/loaisukien")
    public String loaiSK(){
        return "admin/loaiSK";
    }
}
