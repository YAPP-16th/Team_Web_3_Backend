package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Bookmark;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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

    public void delete(Long userId, Long musicianId){

    }

    public Bookmark chkBookmark(String userName, Long musicianId){
        try{
            return em.createQuery("select b from Bookmark  b where b.user.name = :userName and b.musician.id = :musicianId",Bookmark.class)
                    .setParameter("userName",userName)
                    .setParameter("musicianId", musicianId)
                    .getSingleResult();
        }catch (NoResultException e){
            e.printStackTrace();
            return null;
        }

    }
}
