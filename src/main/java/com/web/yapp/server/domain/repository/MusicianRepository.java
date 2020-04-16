package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Musician;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MusicianRepository{
    private EntityManager em;

    public void save(Musician musician){
        EntityManager em = this.em;
        em.persist(musician);
    }
    public Musician findOne(Long id){
        Musician musician = em.find(Musician.class, id);
        return musician;
    }

    public List<Musician> findByName(String name){
        return em.createQuery("select m from TUNA_MUSICIAN m where m.MUSICIAN_NAME = :name", Musician.class)
                .setParameter("name", name)
                .getResultList();
    }


}
