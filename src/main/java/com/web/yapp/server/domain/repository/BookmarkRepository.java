package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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

    public Bookmark chkBookmark(String userName, Long musicianId){
        return em.createQuery("select b from Bookmark  b where b.user.name = :userName and b.musician.id = :musicianId",Bookmark.class)
                .setParameter("userName",userName)
                .setParameter("musicianId", musicianId)
                .getSingleResult();
    }
}
