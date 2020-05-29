//package com.web.yapp.server.controller;
//
//import com.web.yapp.server.domain.Tag;
//import com.web.yapp.server.domain.repository.TagRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//public class TagController {
//    private final TagRepository tagRepository;
//    @GetMapping("/tag")
//    public void saveTags(){
//
//        Tag tag = Tag.builder()
//                .categoryNM("분위기")
//                .tagNM("긴장")
//                .build();
//        tagRepository.save(tag);
//    }
//}
