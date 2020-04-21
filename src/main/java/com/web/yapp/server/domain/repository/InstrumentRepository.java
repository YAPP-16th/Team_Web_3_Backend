package com.web.yapp.server.domain.repository;
import com.web.yapp.server.domain.Instrument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InstrumentRepository {
    private final EntityManager em;
    public List<Instrument> findByAllInstrument() {
        List<Instrument> instrumentsAllInfo = em.createQuery("select m from Instrument m" , Instrument.class)
                .getResultList();
        return instrumentsAllInfo;
    }
}
