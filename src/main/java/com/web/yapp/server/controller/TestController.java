package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.SessionUserDto;
import com.web.yapp.server.domain.Tag;
import com.web.yapp.server.domain.User;
import com.web.yapp.server.domain.repository.MusicianRepository;
import com.web.yapp.server.domain.repository.MusicianTagRepository;
import com.web.yapp.server.domain.repository.TagRepository;
import com.web.yapp.server.domain.repository.UserClassRepository;
import com.web.yapp.server.domain.service.ContractService;
import com.web.yapp.server.domain.service.MusicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestController {
    private final UserClassRepository userClassRepository;
    private final TagRepository tagRepository;
    private final MusicianTagRepository musicianTagRepository;
    private final MusicianService musicianService;
    private final MusicianRepository musicianRepository;
    private final ContractService contractService;

    @GetMapping("/test")
    public void test() {
        Tag tag = Tag.builder()
                .categoryNM("t")
                .tagNM("t")
                .build();
        tagRepository.save(tag);
    }

    @GetMapping("/curation")
    public void curation() {
//        String nm = "공포";
//        Tag tag = tagRepository.findTagByTagNM(nm);
//        System.out.println("id:"+tag.getId());
        Long id = 1L;
        List<Tag> li = musicianTagRepository.findSpclNoteTagByMusician(id);
        System.out.println("작업태그:" + li.get(0).getTagNM());

    }

//    @GetMapping("/main")
//    public Map<String, Object> main(){
//        return musicianService.getMainResponse();
//    }

    @GetMapping("/user")
    public SessionUserDto getUser() {
        User user = userClassRepository.findUserById((long) 1);
        SessionUserDto sessionUserDto = new SessionUserDto(user);
        return sessionUserDto;
    }
}
