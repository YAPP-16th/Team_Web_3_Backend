package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@RequiredArgsConstructor
@Repository
public class SongRepository {
    private final EntityManager em;

    /**
     *
     * @param id
     * @return
     */
    public List<Song> findSongByMusician(Long id){
        return em.createQuery("select s from Song s where s.musician = :id",Song.class)
                .setParameter("id",id)
                .getResultList();
    }
}
