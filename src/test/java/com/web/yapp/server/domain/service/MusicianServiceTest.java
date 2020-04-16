package com.web.yapp.server.domain.service;


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
        musician.setId(1L);
        musician.setCareer("캐리어");
        musician.setName("이름");
        musician.setIntroduction("소개");
        musician.setProfile_url("프로필 URL");

        Long saveId = musicianService.join(musician);
        assertEquals(musician, musicianRepository.findOne(saveId));

    }

    @Test(expected = IllegalStateException.class)
    public void validMusician() throws Exception{
        Musician musician1  = new Musician();
        musician1.setName("kim");

//        Musician musician2 = new Musician();
//        musician2.setName("kim");
//

        // when
        musicianService.join(musician1);
//        musicianService.join(musician2);         //예외 발생 처리
    }
}