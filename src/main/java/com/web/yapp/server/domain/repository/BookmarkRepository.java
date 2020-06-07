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
}
