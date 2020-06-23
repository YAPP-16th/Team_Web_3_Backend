package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Bookmark;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.Song;
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

    public Bookmark save(Bookmark bookmark){
        try{
            EntityManager em = this.em;
            em.persist(bookmark);
            return bookmark;
        }catch (Exception e){
            log.error("BookmarkRepository save :"+e.getMessage());
            return new Bookmark();
        }
    }


    /**
     * id값으로 북마크 정보 조회
     * @param id
     * @return
     */
    /**
     *
     * @param userId
     * @return
     */
    public Bookmark findByUserId(Long userId){
        try{
            return em.createQuery("select b from Bookmark b where b.user.id = :userId", Bookmark.class)
                    .setParameter("userId",userId)
                    .getSingleResult();
        }catch (NoResultException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

 /*   public Bookmark findOne(Long id){
        Bookmark bookmark = em.find(Bookmark.class, id);
        return bookmark;
    }*/

    public int delete(Long userId, Long musicianId){
        try {
            return em.createQuery("delete from Bookmark b where b.user.id = :userId and b.musician.id = :musicianId")
                    .setParameter("userId",userId)
                    .setParameter("musicianId",musicianId)
                    .executeUpdate();
        }catch (NoResultException e){
            log.error("BookmarkRepository chkBookmark :"+e.getMessage());
            return 0;
        }
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
