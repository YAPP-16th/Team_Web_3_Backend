package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Musician;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MusicianRepository{
    private final EntityManager em;


    /**
     * 뮤지션 값 저장
     * @param musician
     */
    public void save(Musician musician){
        EntityManager em = this.em;
        em.persist(musician);
    }

    /**
     * id값으로 뮤지션 조회
     * @param id
     * @return
     */
    public Musician findOne(Long id){
        Musician musician = em.find(Musician.class, id);
        return musician;
    }

    /**
     * 뮤지션 닉네임으로 값 조회
     * @param nickNm
     * @return
     */
    public List<Musician> findByNickNm(String nickNm){
        return em.createQuery("select m from Musician m where m.nickNm = :nickNm", Musician.class)
                .setParameter("nickNm", nickNm)
                .getResultList();
    }

    public Musician findByUserNm(String userNm){
        return em.createQuery("select m from Musician m where m.userId.name = :userNm", Musician.class)
                .setParameter("userNm", userNm)
                .getSingleResult();
    }


    /**
     * 모든값 조회
     * @return
     */
    public List<Musician> findAllMusician(){
        List<Musician> musicianAllInfo = em.createQuery("select m from Musician m" , Musician.class)
                .getResultList();
        return musicianAllInfo;
    }

    /**
     * 큐레이션 값 조회
     * @return
     */
    public List<Musician> findCurationMusician(){
        List<Musician> musicianCurationInfo = em.createQuery("select m from Musician m" , Musician.class)
                .getResultList();
        return musicianCurationInfo;
    }

    /**
     * 새로 등장한 뮤지션
     */
    public List<Musician> findMusicianByBookmark(){
        List<Musician> musicianChoiceInfo;
        try{
            musicianChoiceInfo = em.createQuery("select m from Musician m order by m.bookmarkCount desc"  , Musician.class)
                    .setFirstResult(0)
                    .setMaxResults(9)
                    .getResultList();
        }
        catch (NoResultException e){
            log.error("MusicianRepository findMusicianByBookmark :"+e.getMessage());
            musicianChoiceInfo = null;
        }
        return musicianChoiceInfo;
    }

    /**
     * 리스너들의 선택
     */
    public List<Musician> findMusicianByNew(){
        List<Musician> musicians;
        try{
            musicians = em.createQuery("select m from Musician m order by m.createdDate asc " , Musician.class)
                    .setFirstResult(0)
                    .setMaxResults(9)
                    .getResultList();

        }
        catch (NoResultException e){
            log.error("MusicianRepository findMusicianByNew :"+e.getMessage());
            musicians = null;
        }
        return musicians;


    }

    public void upBookmarkCount(Long musicianId){ //잘못된 뮤지션 id가 들어왔을 때 처리하기
        em.createQuery("update Musician m set m.bookmarkCount = m.bookmarkCount+1 where m.id = :musicianId")
                .setParameter("musicianId",musicianId);
    }

    public void downBookmarkCount(Long musicianId){
        em.createQuery("update Musician m set m.bookmarkCount = m.bookmarkCount-1 where m.id = :musicianId")
                .setParameter("musicianId",musicianId);
    }

}