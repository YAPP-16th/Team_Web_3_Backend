package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.MusicianTag;
import com.web.yapp.server.domain.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MusicianTagRepository {
    private final EntityManager em;

    /**
     * 태그로 뮤지션 찾기
     * @param tagId
     * @return
     */
    public List<Musician> findMusicianByTag(Long tagId){
        List<Musician> musicians;
        try{
            musicians = em.createQuery("select mt.musician from MusicianTag mt where mt.tag.id = :tagId", Musician.class)
                    .setParameter("tagId",tagId)
                    .getResultList();

        }catch (NoResultException e){
            log.error("MusicianTagRepository findMusicianByTag :"+e.getMessage());
            musicians = null;
        }
        return musicians;
    }

    public List<Musician> findNoOptionMusicianByTag(Long tagId, String categoryNM){
        List<Musician> musicians;
        try{
            musicians = em.createQuery("select mt.musician from MusicianTag mt " +
                    "where mt.tag.id = :tagId and mt.categoryNM = :categoryNM", Musician.class)
                    .setParameter("tagId",tagId)
                    .setParameter("categoryNM",categoryNM)
                    .getResultList();

        }catch (NoResultException e){
            log.error("MusicianTagRepository findNoOptionMusicianByTag :"+e.getMessage());
            musicians = null;
        }
        return musicians;
    }
    /**
     * 작업태그 조회
     * @param musicianId
     * @return
     */
    public List<Tag> findSpclNoteTagByMusician(Long musicianId){
        List<Tag> tags;
        try{
            tags = em.createQuery("select mt.tag from MusicianTag " +
                    "mt where mt.musician.id = :musicianId and mt.categoryNM = '작업'",Tag.class)
                    .setParameter("musicianId",musicianId)
                    .getResultList();

        }catch (NoResultException e){
            log.error("MusicianTagRepository findSpclNoteTagByMusician :"+e.getMessage());
            tags = null;
        }
        return tags;
    }

    /**
     * 대표태그 조회
     * @param musicianId
     * @return
     */
    public List<Tag> findRPTagByMusician(Long musicianId){
        List<Tag> tags;
        try{
            tags = em.createQuery("select mt.tag from MusicianTag mt " +
                    "where mt.musician.id = :musicianId and mt.represent = 1 " ,Tag.class)
                    .setParameter("musicianId",musicianId)
                    .getResultList();
        }catch (NoResultException e){
            log.error("MusicianTagRepository findRPTagByMusician :"+e.getMessage());
            tags = null;
        }
        return tags;
    }


    /**
     * 뮤지션이 가진 태그 조회
     * @param musicianId
     * @return
     */
    public List<Tag> findTagByMusician(Long musicianId){
        List<Tag> tags;
        return em.createQuery("select mt.tag from MusicianTag mt where mt.musician.id = :musicianId", Tag.class)
                .setParameter("musicianId",musicianId)
                .getResultList();
    }

    /**
     * 태그등록
     * @param musicianTag
     */
    public void save(MusicianTag musicianTag){
        EntityManager em = this.em;
        em.persist(musicianTag);
    }
}
