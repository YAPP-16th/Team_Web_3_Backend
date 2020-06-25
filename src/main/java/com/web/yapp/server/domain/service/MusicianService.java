package com.web.yapp.server.domain.service;


import com.web.yapp.server.controller.dto.*;
//import com.web.yapp.server.controller.dto.SessionUserDto;
import com.web.yapp.server.domain.*;
import com.web.yapp.server.domain.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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
                             AtmosphereDto atmoList,
                             GenreDto genreList,
                             InstrumentDto instruList,
                             ThemeDto themeList,
                             SpecialDto spclNoteList){



        Musician musician = musicianDto.toEntity();
        List<String> atmoListTmp= atmoList.getTagNM();
        List<String> genreListTmp= genreList.getTagNM();
        List<String> InstrumentDto= instruList.getTagNM();
        List<String> themeListTmp= themeList.getTagNM();
        List<String> spclNoteListTmp= spclNoteList.getTagNM();
        musicianRepository.save(musician);

        musicianTagService.saveMusicianTag(atmoListTmp, musician, "분위기");
        musicianTagService.saveMusicianTag(genreListTmp, musician, "장르");
        musicianTagService.saveMusicianTag(InstrumentDto, musician,"악기");
        musicianTagService.saveMusicianTag(themeListTmp, musician,"테마");
        musicianTagService.saveMusicianTag(spclNoteListTmp, musician,"작업");

        return musician.getId(); //id 값 잘 들어옴
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
        return map;
    }

    /**
     * 검색 응답값
     * @return
     */
    public Map<String, Object> getSearchResponse(){
        HashMap<String, Object> map = new HashMap<>();
        //map.put("searchMusician",findMusicianBySearch());

        SessionUserDto sessionUserDto = (SessionUserDto) httpSession.getAttribute("user");
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
     * @param cuReqDto
     * @return
     */
    public Map<String,Object> musicianCuration(CurationReqDto cuReqDto){
        HashMap<String,Object> ResponseMap = new HashMap<>();
        HashMap<Musician,Integer> curationResult = new HashMap<>();
        List<SimpleMusicianResponseDto> musicianResponseDtos = new LinkedList<SimpleMusicianResponseDto>();
        List<Musician> musicians = new LinkedList<>();
        musicians = musicianTagService.findMusicianByTags(cuReqDto.getAtmoList(), musicians);
        musicians = musicianTagService.findMusicianByTags(cuReqDto.getGenreList(), musicians);
        musicians = musicianTagService.findMusicianByTags(cuReqDto.getInstruList(), musicians);
        musicians = musicianTagService.findMusicianByTags(cuReqDto.getThemeList(), musicians);

        int max = 0;

        for (Musician musician: musicians
        ) {
            int value = curationResult.containsKey(musician) ? curationResult.get(musician)+1 : 1 ;
            curationResult.put(musician,value);
            max = Math.max(max,value);
        }

        //분위기, 장르, 악기, 테마의 조건을 모두 만족하는 뮤지션 고르기
        for( Map.Entry<Musician, Integer> elem : curationResult.entrySet() ){
            if(elem.getValue() == max){
                Musician musician = elem.getKey();
                SimpleMusicianResponseDto simpleMusicianResponseDto
                        = getSimpleMusicianResponseDto(musician);
                musicianResponseDtos.add(simpleMusicianResponseDto);
            }
        }

        ResponseMap.put("musician",musicianResponseDtos);
        return ResponseMap;
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
     * 검색하기 페이지 뮤지션
     * @return
     */
    public List<MusicianCardResponseDto> findMusicianBySearch(String categoryNm){ //좋아요 눌린 여부도 필요?
        List<Musician> musicians = musicianRepository.findMusicianBySearch(categoryNm);
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
        if(httpSession.getAttribute("user") == null) return false; //로그인 안하면 북마크 false되어있는 상태
        SessionUserDto sessionUserDto = (SessionUserDto) httpSession.getAttribute("user");
        String userEmail = sessionUserDto.getEmail();
        Bookmark bookmark = bookmarkRepository.chkBookmark(userEmail, musician.getId());
        if(bookmark == null) log.info("MusicianService chkBookmark bookmark is null");
        Boolean alreadyBookmark = bookmark == null ? false : true;
        return alreadyBookmark;
    }

    /**
     * 뮤지션, 노래, 작업태그, 대표태그 정보
     * @param musician
     * @return
     */
    public SimpleMusicianResponseDto getSimpleMusicianResponseDto(Musician musician){
        MusicianMainResponseDto musicianMainResponseDto = new MusicianMainResponseDto(musician);
        SongMainResponseDto songMainResponseDto = songService.findRPSongByMuscianId(musician.getId());
        List<String> spclNoteTagNMList = musicianTagService.findSpclNoteTagByMusician(musician.getId());
        List<String> RPTag = musicianTagService.findRPTagByMusician(musician.getId());

        return SimpleMusicianResponseDto.builder()
                .musicianMainResponseDto(musicianMainResponseDto)
                .songMainResponseDto(songMainResponseDto)
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