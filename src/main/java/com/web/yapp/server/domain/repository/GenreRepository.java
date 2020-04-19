package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Atmosphere;
import com.web.yapp.server.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreRepository {
    private final EntityManager em;
    public List<Genre> findByAllGenre() {
        List<Genre> genreAllInfo = em.createQuery("select m from Genre m" , Genre.class)
                .getResultList();
        return genreAllInfo;
    }
}
