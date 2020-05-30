package com.web.yapp.server.controller;

import com.web.yapp.server.domain.Tag;
import com.web.yapp.server.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {
    private final TagRepository tagRepository;

    @GetMapping("/test")
    public void test(){
        Tag tag = Tag.builder()
                .categoryNM("t")
                .tagNM("t")
                .build();
        tagRepository.save(tag);
    }



}
