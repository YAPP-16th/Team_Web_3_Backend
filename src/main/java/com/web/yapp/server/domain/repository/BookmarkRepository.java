package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Slf4j
@RequiredArgsConstructor
@Repository
public class BookmarkRepository {
    private final EntityManager em;

    public void save(Bookmark bookmark){
        EntityManager em = this.em;
        em.persist(bookmark);
    }

    public void delete(Long userId, Long musicianId){

    }

    public Bookmark chkBookmark(String userEmail, Long musicianId){
        try{
            return em.createQuery("select b from Bookmark  b where b.user.email = :userEmail and b.musician.id = :musicianId",Bookmark.class)
                    .setParameter("userEmail",userEmail)
                    .setParameter("musicianId", musicianId)
                    .getSingleResult();
        }catch (NoResultException e){
            log.error("BookmarkRepository chkBookmark :"+e.getMessage());
            return null;
        }

    }
}
