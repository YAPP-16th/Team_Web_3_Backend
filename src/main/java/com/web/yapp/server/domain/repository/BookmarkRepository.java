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

    public void save(Bookmark bookmark){
        EntityManager em = this.em;
        em.persist(bookmark);
    }


    /**
     * id값으로 북마크 정보 조회
     * @param id
     * @return
     */
    /**
     *
     * @param user
     * @return
     */
    public Bookmark findByUserId(Long user){
        try{
            return em.createQuery("select b from Bookmark b where b.bookmark.user = :user", Bookmark.class)
                    .setParameter("user",user)
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
