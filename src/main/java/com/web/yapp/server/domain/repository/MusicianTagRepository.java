package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.MusicianTag;
import com.web.yapp.server.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MusicianTagRepository {
    private final EntityManager em;

    //태그로 뮤지션 조회하기
    public List<Musician> findMusicianByTag(Long tagId){
        return em.createQuery("select mt.musician from MusicianTag mt where mt.tag.id = :tagId", Musician.class)
                .setParameter("tagId",tagId)
                .getResultList();
    }

    //작업태그 조회
    public List<Tag> findSpclNoteTagByMusician(Long musicianId){
        //String spclNote = "작업";
        return em.createQuery("select mt.tag from MusicianTag " +
                "mt where mt.musician.id = :musicianId and mt.categoryNM = '작업'",Tag.class)
                .setParameter("musicianId",musicianId)
                .getResultList();
    }

    public List<Tag> findRPTagByMusician(Long musicianId){
        return em.createQuery("select mt.tag from MusicianTag mt where mt.musician.id = :musicianId and mt.represent = 1",Tag.class)
                .setParameter("musicianId",musicianId)
                .getResultList();
    }


    //뮤지션이 가진 태그 조회하기
    public List<Tag> findTagByMusician(Long musicianId){
        return em.createQuery("select mt.tag from MusicianTag mt where mt.musician.id = :musicianId", Tag.class)
                .setParameter("musicianId",musicianId)
                .getResultList();
    }

    //태그등록
    public void save(MusicianTag musicianTag){
        EntityManager em = this.em;
        em.persist(musicianTag);
    }
}