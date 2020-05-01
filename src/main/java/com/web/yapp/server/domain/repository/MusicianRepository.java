package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Musician;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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


    /**
     * 모든값 조회
     * @return
     */
    public List<Musician> findAllMusician(){
        List<Musician> musicianAllInfo = em.createQuery("select m from Musician m" , Musician.class)
                .getResultList();
        return musicianAllInfo;
    }


}
