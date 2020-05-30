package com.web.yapp.server.domain.service;


import com.web.yapp.server.controller.dto.*;
//import com.web.yapp.server.controller.dto.SessionUserDto;
import com.web.yapp.server.domain.*;
import com.web.yapp.server.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

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
    private final MusicianTagService musicianTagService;
    private final SongService songService;

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
        List<String> spclNoteTagNMList = new LinkedList<String>();
        List<String> RPTag = new LinkedList<String>(); //태그 카테고리 순서대로 주기
        List<MusicianSearchResponseDto> musicianResponseDtos = new LinkedList<MusicianSearchResponseDto>();

        musicians.addAll(musicianTagService.findMusicianByTags(atmoList));
        musicians.addAll(musicianTagService.findMusicianByTags(genreList));
        musicians.addAll(musicianTagService.findMusicianByTags(instruList));
        musicians.addAll(musicianTagService.findMusicianByTags(themeList));

        for (Musician musician : musicians
        ) {
            spclNoteTagNMList = musicianTagService.findSpclNoteTagByMusician(musician.getId());
            RPTag = musicianTagService.findRPTagByMusician(musician.getId());
            MusicianDto musicianDto = new MusicianDto(musician);
            SongDto songDto =  songService.findRPSongByMuscianId(musician.getId());

            MusicianSearchResponseDto musicianSearchResponseDto
                    = MusicianSearchResponseDto.builder()
                    .musicianDto(musicianDto)
                    .songDto(songDto)
                    .spclNoteTags(spclNoteTagNMList)
                    .RPtags(RPTag)
                    .build();
            musicianResponseDtos.add(musicianSearchResponseDto);
        }

        map.put("musician",musicianResponseDtos);
        return map;
    }
}
