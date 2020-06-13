package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Contract;
import com.web.yapp.server.domain.Musician;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class ContractRepository {
    private final EntityManager em;

    public void save(Contract contract){
        EntityManager em = this.em;
        em.persist(contract);
    }
}
