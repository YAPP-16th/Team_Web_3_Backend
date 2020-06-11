package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository{
    private final EntityManager em;

    public User findByEmail(String email){
        return em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email",email)
                .getSingleResult();
    }

    public void save(User user){
        EntityManager em = this.em;
        em.persist(user);
    }

    public User findUserById(Long userId){
        return em.createQuery("select u from User u where u.id = :userId",User.class)
                .setParameter("userId",userId)
                .getSingleResult();
    }
}
