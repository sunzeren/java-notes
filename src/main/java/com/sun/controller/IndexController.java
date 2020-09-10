package com.sun.controller;

import com.sun.pojo.SitProjectEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by Sun, Date on 2020/9/10.
 * PS: Not easy to write code, please indicate.
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "", "index", "index.html"})
    public String index(Model model) {
        model.addAttribute("sitUrl", "http://honeyze.ren");
        List<SitProjectEntity> projectList = new ArrayList<>();
        projectList.add(new SitProjectEntity("0", "http://blog.honeyze.ren", "Informal", "Blog Notes", "Learning"));
        projectList.add(new SitProjectEntity("1", "https://blog.csdn.net/HoneyZe", "diary", "CSDN", ""));
        projectList.add(new SitProjectEntity("2", "https://github.com/sunzeren", "Project", "Github", "Some skill"));
        model.addAttribute("projectList", projectList);
        return "index";
    }

}
