package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.ContractTag;
import com.web.yapp.server.domain.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class ContractTagRepository {
    private final EntityManager em;

    public void save(ContractTag contractTag){
        EntityManager em = this.em;
        em.persist(contractTag);
    }
}
