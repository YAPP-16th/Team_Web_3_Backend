package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository{
    private final EntityManager em;

    /**
     * 유저 값 저장
     * @param user
     */
    public void save(User user){
        EntityManager em = this.em;
        em.persist(user);
    }

    /**
     * id값으로 유저 조회
     * @param id
     * @return
     */
    public User findOne(Long id){
        User user = em.find(User.class, id);
        return user;
    }

    /**
     * 이름으로 값 조회
     * @param name
     * @return
     */
    public List<User> findByName(String name){
        return em.createQuery("select m from User m where m.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }


    /**
     * 모든값 조회
     * @return
     */
    public List<User> findAllMusician(){
        List<User> UserAllInfo = em.createQuery("select m from User m" , User.class)
                .getResultList();
        return UserAllInfo;
    }


}

