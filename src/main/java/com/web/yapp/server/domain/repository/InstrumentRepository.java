package com.web.yapp.server.domain.repository;
import com.web.yapp.server.domain.Genre;
import com.web.yapp.server.domain.Instrument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InstrumentRepository {
    private final EntityManager em;
    /**
     * 악기 값 저장
     * @param instrument
     */
    public void save(Instrument instrument){
        EntityManager em = this.em;
        em.persist(instrument);
    }
    public List<Instrument> findByAllInstrument() {
        List<Instrument> instrumentsAllInfo = em.createQuery("select m from Instrument m" , Instrument.class)
                .getResultList();
        return instrumentsAllInfo;
    }
}
