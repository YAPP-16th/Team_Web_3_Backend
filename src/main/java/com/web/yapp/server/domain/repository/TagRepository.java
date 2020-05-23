package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class TagRepository {
    private final EntityManager em;

    public Tag findTagByTagNM(String tagNM){
        return em.createQuery("select t from Tag t where t.tagNm = :tagNM",Tag.class)
                .setParameter("tagNM",tagNM)
                .getSingleResult();
    }
}
