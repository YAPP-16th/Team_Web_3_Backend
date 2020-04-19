package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.MusicianDto;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.service.MusicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MusicianController {
    private final MusicianService musicianService;


    /**
     * 뮤지션 생성 페이지 이동 컨트롤러
     * @param model
     * @return
     */
    @GetMapping("/musicians/new")
    public String createMusicianPage(Model model){
        model.addAttribute("memberForm", new MusicianDto());
        return "createMusicianPage";
    }


    /**
     * 뮤지션 생성
     * @param musicianDto
     * @param result
     * @return
     */
    @PostMapping("/musicians/new")
    public String createMusician(@Valid MusicianDto musicianDto, BindingResult result){

        // @Vaild 체크이후 올바르지 않는 값이 있다면 result에 담겨져 있음.
        if(result.hasErrors()){
            return "musicians/createMusicianPage";      // 페이지 실패처리
        }

        Musician musician = new Musician();
        musician.setCareer(musicianDto.getCareer());
        musician.setName(musicianDto.getName());
        musician.setIntroduction(musicianDto.getIntroduction());
        musician.setProfile_url(musicianDto.getProfile_url());
        musicianService.join(musician);
        return "redirect:/";
    }
}
