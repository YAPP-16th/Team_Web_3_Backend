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
    private final AtmosphereRepository atmosphereRepository;
    private final InstrumentRepository instrumentRepository;
    private final GenreRepository genreRepository;
    private final ThemeRepository themeRepository;

    /**
     * 뮤지션 등록
     * @param musicianDto
     * @param atmoList
     * @param genreList
     * @param instruList
     * @param themeList
     * @return
     */
    @Transactional
    public Long saveRegister(MusicianDto musicianDto,
                             List<AtmosphereDto> atmoList,
                             List<GenreDto> genreList,
                             List<InstrumentDto> instruList,
                             List<ThemeDto> themeList){

        /**
         * 뮤지 카테고리를 제외한 등록
         */
        Musician musician = new Musician();
        musician = musicianDto.toEntity();
        musicianRepository.save(musician);

        /**
         * 카테고리 등록 분위기
         */
        if(atmoList != null ){
            for (AtmosphereDto item:atmoList) {
                Atmosphere atmosphereEntity = Atmosphere.builder()
                        .musicianId(musician)
                        .atmoKindNm(item.getAtmoKindNm())
                        .build();
                atmosphereRepository.save(atmosphereEntity);
            }
        }
        /**
         * 카테고리 등록 장르
         */
        if(genreList != null ){
            for (GenreDto item:genreList) {
                Genre genreEntity = Genre.builder()
                        .musicianId(musician)
                        .genreKindNm(item.getGenreKindNm())
                        .build();
                genreRepository.save(genreEntity);
            }
        }
        /**
         * 카테고리 등록 악기
         */
        if(instruList != null ){
            for (InstrumentDto item:instruList) {
                Instrument instruEntity = Instrument.builder()
                        .musicianId(musician)
                        .instruKindNm(item.getInstruKindNm())
                        .build();
                instrumentRepository.save(instruEntity);
            }
        }
        /**
         * 카테고리 등록 분위기
         */
        if(themeList != null ){
            for (ThemeDto item:themeList) {
                Theme themeEntity = Theme.builder()
                        .musicianId(musician)
                        .themeKindNm(item.getThemeKindNm())
                        .build();
                themeRepository.save(themeEntity);
            }
        }
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
