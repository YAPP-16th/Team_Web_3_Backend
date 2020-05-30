package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.MusicianSearchResponseDto;
import com.web.yapp.server.domain.Tag;
import com.web.yapp.server.domain.repository.MusicianTagRepository;
import com.web.yapp.server.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestController {
    private final TagRepository tagRepository;
    private final MusicianTagRepository musicianTagRepository;

    @GetMapping("/test")
    public void test(){
        Tag tag = Tag.builder()
                .categoryNM("t")
                .tagNM("t")
                .build();
        tagRepository.save(tag);
    }

    @GetMapping("/curation")
    public void curation(){
//        String nm = "공포";
//        Tag tag = tagRepository.findTagByTagNM(nm);
//        System.out.println("id:"+tag.getId());
        Long id = 1L;
        List<Tag> li = musicianTagRepository.findSpclNoteTagByMusician(id);
        System.out.println("작업태그:"+li.get(0).getTagNM());

    }


}
