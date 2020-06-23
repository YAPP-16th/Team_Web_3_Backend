package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Slf4j
@RequiredArgsConstructor
@Repository
public class TagRepository {
    private final EntityManager em;

    public Tag findTagByTagNM(String tagNM){
        try {
            return em.createQuery("select t from Tag t where t.tagNM = :tagNM",Tag.class)
                    .setParameter("tagNM",tagNM)
                    .setMaxResults(1)
                    .getSingleResult();

        } catch (NoResultException nre) {
            log.error("TagRepository.findTagByTagNM("+tagNM+")  error Message : "+nre.getMessage());
        }
        return new Tag();
    }

    public Tag findTagById(Long tagId){
        return em.createQuery("select t from Tag t where t.id = :tagId",Tag.class)
                .setParameter("tagId",tagId)
                .getSingleResult();
    }

    @Transactional
    public void save(Tag tag){
        EntityManager em = this.em;
        em.persist(tag);
    }
}
