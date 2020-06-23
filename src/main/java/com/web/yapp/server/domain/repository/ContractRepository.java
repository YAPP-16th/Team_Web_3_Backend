package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Bookmark;
import com.web.yapp.server.domain.Contract;
import com.web.yapp.server.domain.Musician;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@RequiredArgsConstructor
@Repository
public class ContractRepository {
    private final EntityManager em;

    public void save(Contract contract){
        EntityManager em = this.em;
        em.persist(contract);
    }
    /**
     * id값으로 의뢰서 조회
     * @param id
     * @return
     */
    public Contract findOne(Long id){
        Contract contract = em.find(Contract.class, id);
        return contract;
    }

    public Contract findByUserId(Long user){
        try{
            return em.createQuery("select b from Contract b where b.contract.user = :user", Contract.class)
                    .setParameter("user",user)
                    .getSingleResult();
        }catch (NoResultException e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}
