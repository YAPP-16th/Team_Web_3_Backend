package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.MusicianDto;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.service.MusicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MusicianController {
    private final MusicianService musicianService;


    /**
     * 뮤지션 생성 페이지 이동 컨트롤러
     * @param model
     * @return
     */
//    @RequestMapping("/musicians/new")
//    public String createMusicianPage(Model model){
//        model.addAttribute("memberForm", new MusicianDto());
//        return "createMusicianPage";
//    }

    @RequestMapping("/musicians/info")
    public List<Musician> getMusicianInfo(@RequestParam("id") Long id,
                                          @RequestParam("nicknm") String nicknm){
        List<Musician> musicianListInfo = new ArrayList<>();
        Map<String,Object> musicianList = new HashMap<String,Object>();
        musicianListInfo = musicianService.findAllMusician();
        musicianService.findByIdMusician(id);
        musicianService.findByNameMusician(nicknm);

        musicianList.put("musicianListInfo", musicianListInfo);
        return musicianListInfo;

    }

    /**
     * 뮤지션 생성
     * @param musicianDto
     * @param result
     * @return
     */
    @RequestMapping("/musicians/new")
    public String createMusician(@Valid MusicianDto musicianDto, BindingResult result){

        // @Vaild 체크이후 올바르지 않는 값이 있다면 result에 담겨져 있음.
        if(result.hasErrors()){
            return "musicians/createMusicianPage";      // 페이지 실패처리
        }

        Musician musician = new Musician();
        musician.setCareer(musicianDto.getCareer());
        musician.setNickNm(musicianDto.getNickNm());
        musician.setIntroduction(musicianDto.getIntroduction());
        musician.setProfileUrl(musicianDto.getProfileUrl());
        musicianService.join(musician);

        /* 파일업로드 Save + 뮤지션 id 값 */

        /* 카테고리 별 Save + 뮤지션 id 값 */



        return "redirect:/";
    }
}
