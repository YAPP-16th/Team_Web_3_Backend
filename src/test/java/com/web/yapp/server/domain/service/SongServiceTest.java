//package com.web.yapp.server.domain.service;
//
//import com.web.yapp.server.domain.Musician;
//import com.web.yapp.server.domain.Song;
//import com.web.yapp.server.domain.repository.MusicianRepository;
//import com.web.yapp.server.domain.repository.SongRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import static org.junit.Assert.*;
//import javax.persistence.EntityManager;
//import java.io.File;
//import java.util.LinkedList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//
//public class SongServiceTest {
//    @Autowired SongService songService;
//    @Autowired SongRepository songRepository;
//    @Autowired MusicianService musicianService;
//    @Autowired MusicianRepository musicianRepository;
//    @Autowired EntityManager entityManager;
//
//    @Test
//    public void createSong() throws Exception{
//        Musician musician = new Musician();
//        musician.setCareer("ㅊ");
//        musician.setNickNm("이름");
//        musician.setIntroduction("소개");
//        musician.setProfileUrl("프로필 URL");
//        Long saveId = musicianService.join(musician);
//        Musician musician1 = musicianService.findByIdMusician(saveId);
//        Song song = Song.builder()
//                .musician(musician1)
//                .represent(1)
//                .coverUrl("cover")
//                .songUrl("song")
//                .title("title")
//                .build();
//        List<MultipartFile> multipartFiles = new LinkedList<MultipartFile>();
//        List<Long> li = songService.song(multipartFiles, 1L);
//        System.out.println("------//////-------"+li.get(0));
//    }
//
//}
