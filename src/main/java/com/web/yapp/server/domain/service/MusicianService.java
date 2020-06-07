package com.web.yapp.server.domain.service;


import com.web.yapp.server.controller.dto.*;
//import com.web.yapp.server.controller.dto.SessionUserDto;
import com.web.yapp.server.domain.*;
import com.web.yapp.server.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)     /* 전체 서비스를 Readonly로 처리 단, 회원가입의 경우만 트랜잭션 처리 */
@RequiredArgsConstructor
public class MusicianService {
    private final MusicianRepository musicianRepository;
    private final BookmarkRepository bookmarkRepository;
    private final MusicianTagService musicianTagService;
    private final SongService songService;
    private final HttpSession httpSession;

    /**
     * 뮤지션 등록
     * @param musicianDto
     * @param atmoList
     * @param genreList
     * @param instruList
     * @param themeList
     * @param spclNoteList
     * @return
     */
    @Transactional
    public Long saveRegister(MusicianDto musicianDto,
                             List<String> atmoList,
                             List<String> genreList,
                             List<String> instruList,
                             List<String> themeList,
                             List<String> spclNoteList){

        Musician musician = new Musician();
        musician = musicianDto.toEntity();
        musicianRepository.save(musician);

        musicianTagService.saveMusicianTag(atmoList, musician, "분위기");
        musicianTagService.saveMusicianTag(genreList, musician, "장르");
        musicianTagService.saveMusicianTag(instruList, musician,"악기");
        musicianTagService.saveMusicianTag(themeList, musician,"테마");
        musicianTagService.saveMusicianTag(spclNoteList, musician,"작업");

        return musician.getId();
    }

    /**
     * Main Response
     * [Session userDto or MusicianDto, 리스너의 선택, 등장 새로운 뮤지션]
     * sessionUser 바인딩 필요
     * @return
     */
    public Map<String, Object> getMainResponse(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("newMusician",findMusicianByNew());
        map.put("bestMusician",findMusicianByBookmark());
        SessionUserDto sessionUserDto = (SessionUserDto) httpSession.getAttribute("user");
        if(sessionUserDto.getRole().equals("ROLE_USER")) map.put("user",sessionUserDto);
        else if(sessionUserDto.getRole().equals("ROLE_MUSICIAN")) map.put("musician", findMusicianByUserNm());
        return map;
    }

    /**
     * sessionUser의 이름으로 MusicianDto 가져오기
     * @return
     */
    public MusicianDto findMusicianByUserNm(){
        SessionUserDto sessionUserDto = (SessionUserDto) httpSession.getAttribute("user");
        String userName = sessionUserDto.getName();
        Musician musician = musicianRepository.findByUserNm(userName);
        return new MusicianDto(musician);
    }

    /**
     * 큐레이션
     * @param atmoList
     * @param genreList
     * @param instruList
     * @param themeList
     * @return
     */
    public Map<String,Object> musicianCuration(List<String> atmoList, List<String> genreList, List<String> instruList, List<String> themeList){
        HashMap<String,Object> map = new HashMap<>();
        List<Musician> musicians = new LinkedList<Musician>();
        List<SimpleMusicianResponseDto> musicianResponseDtos = new LinkedList<SimpleMusicianResponseDto>();
        HashMap<Musician,Integer> curationResult = new HashMap<>();

        List<Musician> atmoMusician = musicianTagService.findMusicianByTags(atmoList);
        List<Musician> genreMusician = musicianTagService.findMusicianByTags(genreList);
        List<Musician> instruMusician = musicianTagService.findMusicianByTags(instruList);
        List<Musician> themeMusician = musicianTagService.findMusicianByTags(themeList);

        for (Musician musician : atmoMusician) { curationResult.put(musician,0);}
        for (Musician musician : genreMusician) { curationResult.put(musician,0);}
        for (Musician musician : instruMusician) { curationResult.put(musician,0);}
        for (Musician musician : themeMusician) { curationResult.put(musician,0);}

        for( Map.Entry<Musician, Integer> elem : curationResult.entrySet() ){
            Musician musician = elem.getKey();
            SimpleMusicianResponseDto simpleMusicianResponseDto
                    = getSimpleMusicianResponseDto(musician);
            musicianResponseDtos.add(simpleMusicianResponseDto);
        }

        map.put("musician",musicianResponseDtos);
        return map;
    }

    /**
     * 리스너들의 선택
     * @return
     */
    public List<MusicianCardResponseDto> findMusicianByBookmark(){
        List<Musician> musicians = musicianRepository.findMusicianByBookmark();
        return getMusicianCardResponseDto(musicians);
    }

    /**
     * 등장 새로운 뮤지션
     * @return
     */
    public List<MusicianCardResponseDto> findMusicianByNew(){ //좋아요 눌린 여부도 필요?
        List<Musician> musicians = musicianRepository.findMusicianByNew();
        return getMusicianCardResponseDto(musicians);
    }

    /**
     * 메인 뮤지션 카드( 뮤지션, 노래, 작업태그, 대표태그, 좋아요 개수, 좋아요 T/F)
     * @param musicians
     * @return
     */
    public List<MusicianCardResponseDto> getMusicianCardResponseDto(List<Musician> musicians){
        List<MusicianCardResponseDto> musicianCardResponseDtoList = new LinkedList<MusicianCardResponseDto>();
        for (Musician musician: musicians
        ) {
            SimpleMusicianResponseDto simpleMusicianResponseDto = getSimpleMusicianResponseDto(musician);
            Long bookmarkCount = musician.getBookmarkCount();
            MusicianCardResponseDto musicianCardResponseDto = MusicianCardResponseDto.builder()
                    .simpleMusicianResponseDto(simpleMusicianResponseDto)
                    .bookmarkCount(bookmarkCount)
                    .alreadyBookmark(chkBookmark(musician))
                    .build();
            musicianCardResponseDtoList.add(musicianCardResponseDto);
        }
        return musicianCardResponseDtoList;
    }

    /**
     * sessionUser 바인딩 처리 필요
     * @param musician
     * @return
     */
    public boolean chkBookmark(Musician musician){
        SessionUserDto sessionUserDto = (SessionUserDto) httpSession.getAttribute("user");
        String userName = sessionUserDto.getName();
        Bookmark bookmark = bookmarkRepository.chkBookmark(userName, musician.getId());
        Boolean alreadyBookmark = bookmark == null ? false : true;
        return alreadyBookmark;
    }

    /**
     * 뮤지션, 노래, 작업태그, 대표태그 정보
     * @param musician
     * @return
     */
    public SimpleMusicianResponseDto getSimpleMusicianResponseDto(Musician musician){
        MusicianDto musicianDto = new MusicianDto(musician);
        SongDto songDto = songService.findRPSongByMuscianId(musician.getId());
        List<String> spclNoteTagNMList = musicianTagService.findSpclNoteTagByMusician(musician.getId());
        List<String> RPTag = musicianTagService.findRPTagByMusician(musician.getId());

        return SimpleMusicianResponseDto.builder()
                .musicianDto(musicianDto)
                .songDto(songDto)
                .spclNoteTags(spclNoteTagNMList)
                .RPtags(RPTag)
                .build();
    }

    //중복 회원 체크
    private void validateDuplicateMusician(Musician musician) {
        // EXCEPTION
        List<Musician> findMusicians = musicianRepository.findByNickNm(musician.getNickNm());           // 유니크 제약조건을 먹어주는게 동시회원가입을 막을 수 있다.

        if(!findMusicians.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    /**
     * 뮤지션 전체 조회
     * @return
     */
    public List<MusicianDto> findAllMusician(){
        return musicianRepository.findAllMusician().stream()
                .map(MusicianDto::new)
                .collect(Collectors.toList());

    }

    /**
     * 뮤지션 ID 조회
     * @param id
     * @return
     */

    public MusicianDto findByIdMusician(Long id){
        return new MusicianDto(musicianRepository.findOne(id));
    }


    /**
     * 뮤지션 닉네임 조회
     * @param nickNm
     * @return
     */
    public List<MusicianDto> findByNickNmMusician(String nickNm) {
        return musicianRepository.findByNickNm(nickNm).stream()
                .map(MusicianDto::new)
                .collect(Collectors.toList());
    }
}