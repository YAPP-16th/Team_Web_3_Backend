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
        musician.setName("이름");
        musician.setIntroduction("소개");
        musician.setProfile_url("프로필 URL");

        Long saveId = musicianService.join(musician);
        entityManager.flush();                                          // roleback 값들 확인
        Musician one = musicianRepository.findOne(saveId);
        List<Musician> allMusician = musicianRepository.findAllMusician();
        List<Musician> byName = musicianRepository.findByName(musician.getName());

        assertEquals(musician, musicianRepository.findOne(saveId));     // join return ID값 조회
        assertEquals(musician, one);                                    // ID값 조회
        assertEquals(musician.getName(), byName.get(0).getName());      // 이름값 조회
        assertEquals(musician,allMusician.get(0));                      // 모든 유저


    }

    @Test(expected = IllegalStateException.class)
    public void validMusician() throws Exception{
        Musician musician  = new Musician();
        musician.setCareer("캐리어");
        musician.setName("이름");
        musician.setIntroduction("소개");
        musician.setProfile_url("프로필 URL");

        Musician musician1  = new Musician();
        musician1.setCareer("캐리어");
        musician1.setName("이름1");
        musician1.setIntroduction("소개");
        musician1.setProfile_url("프로필 URL");

        // when
        musicianService.join(musician);
        musicianService.join(musician1);         //예외 발생 처리
    }
}