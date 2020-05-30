package com.web.yapp.server.domain.service;

import com.web.yapp.server.controller.dto.MusicianDto;
import com.web.yapp.server.controller.dto.MusicianSearchResponseDto;
import com.web.yapp.server.controller.dto.SongDto;
import com.web.yapp.server.controller.dto.TagDto;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.MusicianTag;
import com.web.yapp.server.domain.Tag;
import com.web.yapp.server.domain.repository.MusicianRepository;
import com.web.yapp.server.domain.repository.MusicianTagRepository;
import com.web.yapp.server.domain.repository.TagRepository;
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
public class MusicianTagService {
    private final MusicianRepository musicianRepository;
    private final MusicianTagRepository musicianTagRepository;
    private final TagRepository tagRepository;
    private final SongService songService;

    /**
     * 태그등록
     * @param tagList
     * @param musician
     * @param categoryNM
     */
    public void saveMusicianTag(List<String> tagList, Musician musician, String categoryNM){
        for(int i=0;i<tagList.size();i++){
            String tagNM = tagList.get(i);
            Tag tag = tagRepository.findTagByTagNM(tagNM);
            MusicianTag musicianTag;

            musicianTag = MusicianTag.builder()
                    .musician(musician)
                    .tag(tag)
                    .represent(0)
                    .categoryNM(categoryNM)
                    .build();

            if(i==0)  { //대표태그
                musicianTag = MusicianTag.builder()
                        .musician(musician)
                        .tag(tag)
                        .represent(1)
                        .categoryNM(categoryNM)
                        .build();
            }
            musicianTagRepository.save(musicianTag);
        }
    }


    /**
     * 대표태그 조회
     * @param musicianId
     * @return
     */
    public List<String> findRPTagByMusician(Long musicianId){
        List<Tag> RPTags = musicianTagRepository.findRPTagByMusician(musicianId);
        List<String> RPTagNMList = new LinkedList<String>();

        for (Tag tag: RPTags
        ) {
            RPTagNMList.add(tag.getTagNM());
        }
        return RPTagNMList;
    }

    /**
     * 태그리스트로 뮤지션 조회
     * 큐레이션, 탐색에 필요
     * @param tagList
     * @return
     */
    public List<Musician> findMusicianByTags(List<String> tagList){
        List<Musician> musicianList = new LinkedList<Musician>();
        Map<Musician,Integer> map = new HashMap<>();

        for (String nm:tagList
             ) {
            System.out.println("////////태그이름"+nm);
        }
        for (int i=0;i<tagList.size();i++){
            if(tagList.get(i).equals("선택안함")) break; //선택안함이면 필터링 과정 필요 없음
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

//        return musicianList.stream()
//                .map(MusicianDto::new)
//                .collect(Collectors.toList());
        return musicianList;
    }

    /**
     * 작업태그 조회
     * @param musicianId
     * @return
     */
    public List<String> findSpclNoteTagByMusician(Long musicianId){
        List<Tag> tags = musicianTagRepository.findSpclNoteTagByMusician(musicianId);
        List<String> tagNMList = new LinkedList<String>();
        for (Tag tag:tags
        ) {
            tagNMList.add(tag.getTagNM());
        }
        return tagNMList;
    }

    /**
     * 뮤지션이 가진 태그 조회
     * @param musicianId
     * @return
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
            switch (t.getCategoryNM()){
                case "테마":
                    themeList.add(t.getTagNM());
                    break;
                case "장르":
                    genreList.add(t.getTagNM());
                    break;
                case "분위기":
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