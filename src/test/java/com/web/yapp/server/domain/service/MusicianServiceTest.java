package com.web.yapp.server.domain.service;


import com.web.yapp.server.controller.dto.*;
import com.web.yapp.server.domain.*;
import com.web.yapp.server.domain.repository.MusicianRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;
import javax.persistence.EntityManager;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class MusicianServiceTest {

    @Autowired MusicianService musicianService;
    @Autowired MusicianRepository musicianRepository;
    @Autowired EntityManager entityManager;

//    @Test
//    public void createMusician() throws Exception{
//
//        MusicianDto musicianDto = new MusicianDto();
//        musicianDto.setCareer("캐리어");
//        musicianDto.setNickNm("이름");
//        musicianDto.setIntroduction("소개");
//        musicianDto.setProfileUrl("profile url");
//
//        Musician musician = new Musician();
//
////        musician.setId(1L);
//            /*
//        빌더패턴 적용
//         */
////        Musician.builder().career("캐리어")
////                .nickNm("닉네임")
////                .introduction("소개")
////                .profileUrl("프로필 URL")
////                .build();
////
//
////        musician.setCareer("캐리어");
////        musician.setNickNm("이름");
////        musician.setIntroduction("소개");
////        musician.setProfileUrl("프로필 URL");
//
////        String str1 = musician.getNickNm();
////        String str2 = musician.getIntroduction();
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("a","a");
//        map.put("b","a");
//        map.put("c","a");
//
//        AtmosphereDto mItem = new AtmosphereDto(); // <-- instantiate a new Item
//        mItem.setAtmoKindNm("Hammer");
//
//        GenreDto mItem1 = new GenreDto(); // <-- instantiate a new Item
//        mItem1.setGenreKindNm("Hammer");
//
//        InstrumentDto mItem2 = new InstrumentDto(); // <-- instantiate a new Item
//        mItem2.setInstruKindNm("Hammer");
//
//        ThemeDto mItem3 = new ThemeDto(); // <-- instantiate a new Item
//        mItem3.setThemeKindNm("Hammer");
//
//
//
//        List<AtmosphereDto> atmosphereList = new ArrayList<>();
//        List<GenreDto> genreList = new ArrayList<>();
//        List<InstrumentDto> instrumentList= new ArrayList<>();
//        List<ThemeDto> themeList1 = new ArrayList<>();
//        atmosphereList.add(mItem);
//        genreList.add(mItem1);
//        instrumentList.add(mItem2);
//        themeList1.add(mItem3);
//        // 뮤지션 등록
//        Long saveId = musicianService.saveRegister(musicianDto,atmosphereList,genreList,instrumentList,themeList1);
//        // 파일업로드 로직
//        // 필수 값 null
//        musician = musicianDto.toEntity();
//
//
//        entityManager.flush();                                          // roleback 값들 확인
//        Musician one = musicianRepository.findOne(saveId);
//        List<Musician> allMusician = musicianRepository.findAllMusician();
//        List<Musician> byName = musicianRepository.findByNickNm(musician.getNickNm());
////
////        assertEquals(musician, musicianRepository.findOne(saveId));     // join return ID값 조회
////        assertEquals(musician, one);                                    // ID값 조회
////        assertEquals(musician.getNicknm(), byName.get(0).getNicknm());      // 이름값 조회
////        assertEquals(musician,allMusician.get(0));                      // 모든 유저
//
//
//    }

//    @Test(expected = IllegalStateException.class)
//    public void validMusician() throws Exception{
//        Musician musician  = new Musician();
//        musician.setCareer("캐리어");
//        musician.setNickNm("이름");
//        musician.setIntroduction("소개");
//        musician.setProfileUrl("프로필 URL");
//
//        Musician musician1  = new Musician();
//        musician1.setCareer("캐리어");
//        musician1.setNickNm("이름1");
//        musician1.setIntroduction("소개");
//        musician1.setProfileUrl("프로필 URL");
//
//        // when
//        musicianService.join(musician);
//        musicianService.join(musician1);         //예외 발생 처리
//    }


//    @Test
//    public void curation() throws Exception{
//        List<MusicianDto> list = new LinkedList<MusicianDto>();
//        List<String> taglist = new LinkedList<String>();
//        taglist.add("기타");
//        taglist.add("힙합");
//        list = musicianService.findCurationMusician(taglist);
//        System.out.println("========="+list.get(0).getNickNm());
//    }
}
