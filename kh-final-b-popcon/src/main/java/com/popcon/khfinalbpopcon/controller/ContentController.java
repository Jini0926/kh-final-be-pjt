package com.popcon.khfinalbpopcon.controller;

import com.popcon.khfinalbpopcon.model.Content;
import com.popcon.khfinalbpopcon.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main")
public class ContentController {
    @Autowired
    ContentRepository contentRepository;

    @GetMapping("/explore/listPage")
    public String getAllContent() {
        List<Content> contents = contentRepository.findAll();


        return "";
    }

}
