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

    //특정 태그가진 뮤지션리스트
    public List<Tag> findTagByMusician(Long tagId){
        return em.createQuery("select mt from MusicianTag mt where mt.id = :tagId")
                .setParameter("tagId",tagId)
                .getResultList();
    }

    public void save(MusicianTag musicianTag){
        EntityManager em = this.em;
        em.persist(musicianTag);
    }
}
