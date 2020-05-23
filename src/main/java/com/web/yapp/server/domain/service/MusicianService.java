package com.web.yapp.server.domain.service;


import com.web.yapp.server.controller.dto.*;
//import com.web.yapp.server.controller.dto.SessionUserDto;
import com.web.yapp.server.domain.*;
import com.web.yapp.server.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)     /* 전체 서비스를 Readonly로 처리 단, 회원가입의 경우만 트랜잭션 처리 */
@RequiredArgsConstructor
public class MusicianService {
    private final MusicianRepository musicianRepository;
    private final MusicianTagRepository musicianTagRepository;
    private final TagRepository tagRepository;

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

        /**
         * 뮤지 카테고리를 제외한 등록
         */
        Musician musician = new Musician();
        musician = musicianDto.toEntity();
        musicianRepository.save(musician);
        saveMusicianTag(atmoList, musician);
        saveMusicianTag(genreList, musician);
        saveMusicianTag(instruList, musician);
        saveMusicianTag(themeList, musician);
        saveMusicianTag(spclNoteList, musician);

        return musician.getId();
    }

    public void saveMusicianTag(List<String> tagList, Musician musician){
        for(int i=0;i<tagList.size();i++){
            String tagNM = tagList.get(i);
            Tag tag = tagRepository.findTagByTagNM(tagNM);
            MusicianTag musicianTag;
            musicianTag = MusicianTag.builder()
                    .musician(musician)
                    .tag(tag)
                    .represent(0)
                    .build();

            if(i==0)  { //대표태그
                musicianTag = MusicianTag.builder()
                        .musician(musician)
                        .tag(tag)
                        .represent(1)
                        .build();
            }
            musicianTagRepository.save(musicianTag);
        }
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
     * 뮤지션 큐레이션 조회
     * @return
     * @param atmoList
     * @param genreList
     * @param instruList
     * @param themeList
     */
    public List<MusicianDto> findCurationMusician(List<AtmosphereDto> atmoList, List<GenreDto> genreList, List<InstrumentDto> instruList, List<ThemeDto> themeList){
        return musicianRepository.findAllMusician().stream()
                .map(MusicianDto::new)
                .collect(Collectors.toList());

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
