package com.web.yapp.server.domain.service;


import com.web.yapp.server.controller.dto.*;
//import com.web.yapp.server.controller.dto.SessionUserDto;
import com.web.yapp.server.domain.*;
import com.web.yapp.server.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 뮤지션 큐레이션, 탐색 조회
     * 태그리스트 받아와서 검색
     * @param tagList
     * @return
     */
    public List<MusicianDto> findMusicianByTags(List<String> tagList){
        List<Musician> musicianList = new LinkedList<Musician>();
        Map<Musician,Integer> map = new HashMap<>();

        for (int i=0;i<tagList.size();i++){
            if(tagList.get(i).equals("선택안함")) continue;
            Tag tag = tagRepository.findTagByTagNM(tagList.get(i));
            Long tagId = tag.getId();
            List<Musician> musicians = musicianTagRepository.findMusicianByTag(tagId);

            //제한없음 태그를 가진 뮤지션도 모두 불러오기
            Tag possibleTag = tagRepository.findTagByTagNM("제한없음");
            Long possibleTagId = possibleTag.getId();
            List<Musician> possibleMuiscians = musicianTagRepository.findMusicianByTag(possibleTagId);

            for (Musician musician : musicians
            ) {
                int value = map.containsKey(musician) ? map.get(musician)+1 : 1 ;
                map.put(musician,value);
            }

            for (Musician musician : possibleMuiscians
                 ) {
                map.put(musician, 0);
            }

        }

        for( Map.Entry<Musician, Integer> elem : map.entrySet() ){
           if(elem.getValue() == tagList.size() || elem.getValue() == 0){ //선택한 태그를 모두가지고 있는 뮤지션이면
               musicianList.add(elem.getKey());
           }
        }

        return musicianList.stream()
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

    /**
     * 뮤지션이 가진 태그 조회
     * @param musicianId
     * @return
     * /*
     *     * 0 : 특이사항(작업)
     *     * 1 : 테마
     *     * 2 : 장르
     *     * 3 : 분위기
     *     * 4 : 악기
     *     *
     */
    public Map<String, Object> findTagByMusician(Long musicianId){
        List<TagDto> tags = musicianTagRepository.findTagByMusician(musicianId).stream()
                .map(TagDto::new)
                .collect(Collectors.toList());
        Map<String,Object> map = new HashMap<String, Object>();
        List<String> themeList = new LinkedList<String>();
        List<String> genreList = new LinkedList<String>();
        List<String> atmoList = new LinkedList<String>();
        List<String> instruList = new LinkedList<String>();
        for (TagDto t:tags
             ) {
            switch (t.getCategory()){
                case 1:
                    themeList.add(t.getTagNM());
                    break;
                case 2:
                    genreList.add(t.getTagNM());
                    break;
                case 3:
                    atmoList.add(t.getTagNM());
                    break;
                default:
                    instruList.add(t.getTagNM());
                    break;
            }
        }
        map.put("theme",themeList);
        map.put("genre",genreList);
        map.put("atmo",atmoList);
        map.put("instru",instruList);
        return map;
    }
}
