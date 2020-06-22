package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.*;
import com.web.yapp.server.domain.Role;
import com.web.yapp.server.domain.User;
import com.web.yapp.server.domain.repository.UserClassRepository;
import com.web.yapp.server.domain.repository.UserRoleRepository;
import com.web.yapp.server.domain.service.MusicianService;
import com.web.yapp.server.domain.service.MusicianTagService;
import com.web.yapp.server.domain.service.SongService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class MusicianController {
    private final HttpSession httpSession;
    private final MusicianTagService musicianTagService;
    private final MusicianService musicianService;
    private final UserClassRepository userClassRepository;
    private final UserRoleRepository userRoleRepository;
    private final SongService songService;


    /**
     *  뮤지션 값 전체조회
     *
     * @return
     */
    @RequestMapping(value = "/musicians", method=RequestMethod.GET)
    public Map<String,Object> getMusicianAllInfo(){
        List<MusicianDto> musicianListAllInfo = new ArrayList<>();
        Map<String,Object> musicianList = new HashMap<String,Object>();
        musicianListAllInfo = musicianService.findAllMusician();
        musicianList.put("musicianListAllInfo", musicianListAllInfo);
        return musicianList;

    }

    // 뮤지션 상세프로필과 마이페이지 별도로 API 필요
    /**
     * 뮤지션 id값 조회
     * @param id
     *
     * @returnS
     */
    @RequestMapping(value = "/musicians/v1/{id}", method=RequestMethod.GET)
    public Map<String,Object> getMusicianIdInfo(@PathVariable("id") Long id){
        MusicianDto musicianListIdInfo = new MusicianDto();
        Map<String,Object> musicianList = new HashMap<String,Object>();
        musicianListIdInfo = musicianService.findByIdMusician(id);
        musicianList.put("musicianListIdInfo", musicianListIdInfo);
        return musicianList;
    }

    @GetMapping("/msuician/detail")
    public HashMap<String,Object> getMusicianDetail(@RequestParam Long musicianId){

        HashMap<String,Object> resultMap = new HashMap<String,Object>();

        MusicianDto musicianDto = musicianService.findByIdMusician(musicianId);
        List<SongDto> songList= songService.findSongByMusicianId(musicianId);
        resultMap.put("musicianList", musicianDto);
        resultMap.put("songList", songList);
        return resultMap;
    }

//    /**
//     * 뮤지션 닉네임값 조회
//     * @param nickNm
//     * @return
//     */
//    @RequestMapping(value = "/musicians/v2/{nickNm}", method=RequestMethod.GET)
//    public Map<String,Object> getMusicianNickNmInfo(@PathVariable("nickNm") String nickNm){
//        List<MusicianDto> musicianListNickNmInfo = new ArrayList<>();
//        Map<String,Object> musicianList = new HashMap<String,Object>();
//        musicianListNickNmInfo = musicianService.findByNickNmMusician(nickNm);
//        musicianList.put("musicianListNickNmInfo",musicianListNickNmInfo);
//        return musicianList;
//    }


    /**
     *
     * @return
     */
   /* @RequestMapping(value = "/musicians", method=RequestMethod.POST)
    public List<Map<String, Object>> createMusician(
                                                    HttpSession session,
                                                    @RequestBody HashMap<String,Object> paramMap
                                                    *//*@RequestBody(required = false) List<String> atmoList,
                                                    @RequestBody(required = false) List<String> genreList,
                                                    @RequestBody(required = false) List<String> instruList,
                                                    @RequestBody(required = false) List<String> themeList,
                                                    @RequestBody(required = false) List<String> spclNoteList*//*
    ){
        //노래 파일 받아와서 s3 업로드 후, song Entity create 하기
        *//*
        * musician, 노래파일, 태그 받아오기
        * *//*

        // 맵가져오기
        List<Map<String,Object>> resultMapList = new ArrayList<>();
        Map<String,Object> resultMap = new HashMap<>();
        SessionUserDto user = (SessionUserDto) httpSession.getAttribute("user");
        String accessToken = (String) httpSession.getAttribute("accessToken");





        //Long musicianId = musicianService.saveRegister(musicianDto,atmoList,genreList,instruList,themeList,spclNoteList);
        System.out.println(paramMap);

        // 이메일값으로 유저 역할 조회, 뮤지션으로 Role 변경
        resultMap.put("success", "1");
        String userEmail = user.getEmail();
        User user_role = userRoleRepository.findByEmail(userEmail);
        user_role.setRole(Role.MUSICIAN);
        userClassRepository.save(user_role);

*//*        // 뮤지션, 태그 저장 - return 뮤지션 ID
        musicianService.saveRegister();
        // 곡 정보 저장 - 곡정보, 뮤지션 ID
        songService.songSave();*//*

        resultMapList.add(resultMap);
        return resultMapList;
    }*/


    // 파일업로드 부분 따로

    /*@RequestMapping(value = "/musicians/upload", method=RequestMethod.POST)
    @PostMapping
    public List<Map<String, Object>> createMusicianUpload(@RequestParam MultipartFile[] files
    ){

        List<MultipartFile> multipartFilesList = new ArrayList<>();
        MultipartFile multipartFile = new MultipartFile()

        songService.songSave(songDto, musicianId);
      return songDto;
    }*/


    // 뮤지션 저장부분 따로

    /**
     *
     * @return
     */
    @PostMapping(value = "/musicians",consumes = "multipart/form-data")
    public List<Map<String, Object>> createMusician(HttpSession session,
                                                    @RequestBody MusicianDto musicianDto
                                                    //,@RequestBody AtmosphereDto atmoList
                                                    /*@RequestBody(required = false)List<String> atmoList,
                                                    @RequestBody(required = false) List<String> genreList,
                                                    @RequestBody(required = false) List<String> instruList,
                                                    @RequestBody(required = false) List<String> themeList,
                                                    @RequestBody(required = false) List<String> spclNoteList*/
    ){
        AtmosphereDto atmosphereList = musicianDto.getAtmosphereList();
        GenreDto genreList = musicianDto.getGenreList();
        InstrumentDto instrumentList = musicianDto.getInstrumentList();
        ThemeDto themeList = musicianDto.getThemeList();
        SpecialDto specialList = musicianDto.getSpecialList();
        List<MultipartFile> multipartFiles = musicianDto.getMultipartFiles();

        /*List<MultipartFile> portfolioFile = musicianDto.getPortfolioFile();
        List<MultipartFile> mainSongFile = musicianDto.getMainSongFile();
        List<MultipartFile> subSongFile = musicianDto.getSubSongFile();*/

        //노래 파일 받아와서 s3 업로드 후, song Entity create 하기
        /*
         * musician, 노래파일, 태그 받아오기
         * */
        // 세션 처리
        List<Map<String,Object>> resultMapList = new ArrayList<>();
        Map<String,Object> resultMap = new HashMap<>();

        SessionUserDto user = (SessionUserDto) httpSession.getAttribute("user");
        String accessToken = (String) httpSession.getAttribute("accessToken");
        try {
        System.out.println(multipartFiles);

        //Long userId = musicianDto.getUserId().getId();
        // 1. 뮤지션등록 - 뮤지션 모델 카테고리별 종류
        //Long musicianId = 0L;
        Long musicianId = musicianService.saveRegister(musicianDto,atmosphereList,genreList,instrumentList,themeList,specialList);           // 뮤지션 id값 채번
        songService.songSave(multipartFiles, musicianId);
        // 2. 곡 등록
        //songService.songSave(songDto, musicianId);
        // 이메일값으로 유저 역할 조회, 뮤지션으로 Role 변경
        if(musicianId != null){
            resultMap.put("success", "1");
            resultMap.put("musicianId", musicianId);
            if(user != null){
                String userEmail = user.getEmail();
                User user_role = userRoleRepository.findByEmail(userEmail);
                user_role.setRole(Role.MUSICIAN);

/*                songService.songSave(portfolioFile, musicianId);
                songService.songSave(mainSongFile, musicianId);
                if(subSongFile != null){
                    songService.songSave(subSongFile, musicianId);
                }*/
            }
            //userClassRepository.save(user_role);
        }else {
            resultMap.put("success","0");
        }
        // 뮤지션, 태그 저장 - return 뮤지션 ID
        //musicianService.saveRegister();
        // 곡 정보 저장 - 곡정보, 뮤지션 ID


        } catch (IOException e) {
            e.printStackTrace();
        }
        resultMapList.add(resultMap);
        return resultMapList;
    }
    /**
     *
     * @param atmoList
     * @param genreList
     * @param instruList
     * @param themeList
     * @return
     */
    @GetMapping("/musicians/curation") //뮤지션 + 작업태그 돌려주기
    public Map<String,Object> musicianCuration(@RequestParam List<String> atmoList, @RequestParam List<String> genreList,
                                               @RequestParam List<String> instruList, @RequestParam List<String> themeList) {

        return musicianService.musicianCuration(atmoList, genreList, instruList, themeList);
    }

    /**
     * 뮤지션이 가진 태그 조회
     * @param id
     * @return
     */
    @RequestMapping(value = "/musicians/tag/{id}", method=RequestMethod.GET)
    public Map<String, Object> getTagsByMusician(@PathVariable("id") Long id){
        return musicianTagService.findTagByMusician(id);
    }



    /**
     * 뮤지션 리스너들의 선택
     *
     */

//    @RequestMapping(value = "/musicians/choice", method=RequestMethod.GET)
//    public List<Object> getMusicianChoice(){
//
//        List<Object> musicianChoiceInfoMap = new ArrayList<>();
//
//        musicianChoiceInfoMap = musicianService.findMusicianByChoice();
//        return musicianChoiceInfoMap;
//    }

    /**
     * 등장 새로운 뮤지션
     *
     */

//    @RequestMapping(value = "/musicians/new", method=RequestMethod.GET)
//    public List<Object> getMusicianNew(){
//        List<Object> musicianChoiceInfoMap = new ArrayList<>();
//        musicianChoiceInfoMap = musicianService.findMusicianByNew();
//        return musicianChoiceInfoMap;
//    }
}