package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Atmosphere;
import com.web.yapp.server.domain.Musician;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AtmosphereRepository {
    private final EntityManager em;

    /**
     * 분위기 값 저장
     * @param atmosphere
     */
    public void save(Atmosphere atmosphere){
        EntityManager em = this.em;
        em.persist(atmosphere);
    }
    public List<Atmosphere> findByAllAtmosphere() {
        List<Atmosphere> atomsphereAllInfo = em.createQuery("select m from Atmosphere m" , Atmosphere.class)
                .getResultList();
        return atomsphereAllInfo;
    }
}
