package com.web.yapp.server.domain.service;


import com.web.yapp.server.controller.dto.MusicianDto;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.repository.MusicianRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;
import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class MusicianServiceTest {

    @Autowired MusicianService musicianService;
    @Autowired MusicianRepository musicianRepository;
    @Autowired EntityManager entityManager;

    @Test
    public void createMusician() throws Exception{

        Musician musician = new Musician();

//        musician.setId(1L);
        musician.setCareer("캐리어");
        musician.setNickNm("이름");
        musician.setIntroduction("소개");
        musician.setProfileUrl("프로필 URL");

        String str1 = musician.getNickNm();
        String str2 = musician.getIntroduction();


        // 뮤지션 등록
        Long saveId = musicianService.join(musician);
        // 파일업로드 로직
        // 필수 값 null



        entityManager.flush();                                          // roleback 값들 확인
        Musician one = musicianRepository.findOne(saveId);
        List<Musician> allMusician = musicianRepository.findAllMusician();
        List<Musician> byName = musicianRepository.findByName(musician.getNickNm());
//
//        assertEquals(musician, musicianRepository.findOne(saveId));     // join return ID값 조회
//        assertEquals(musician, one);                                    // ID값 조회
//        assertEquals(musician.getNicknm(), byName.get(0).getNicknm());      // 이름값 조회
//        assertEquals(musician,allMusician.get(0));                      // 모든 유저


    }

    @Test(expected = IllegalStateException.class)
    public void validMusician() throws Exception{
        Musician musician  = new Musician();
        musician.setCareer("캐리어");
        musician.setNickNm("이름");
        musician.setIntroduction("소개");
        musician.setProfileUrl("프로필 URL");

        Musician musician1  = new Musician();
        musician1.setCareer("캐리어");
        musician1.setNickNm("이름1");
        musician1.setIntroduction("소개");
        musician1.setProfileUrl("프로필 URL");

        // when
        musicianService.join(musician);
        musicianService.join(musician1);         //예외 발생 처리
    }
}