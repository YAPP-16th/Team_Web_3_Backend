package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Atmosphere;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AtmosphereRepository {
    private final EntityManager em;
    public List<Atmosphere> findByAllAtmosphere() {
        List<Atmosphere> atomsphereAllInfo = em.createQuery("select m from Atmosphere m" , Atmosphere.class)
                .getResultList();
        return atomsphereAllInfo;
    }
}
