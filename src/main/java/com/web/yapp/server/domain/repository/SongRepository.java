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
     * @param musicianId
     * @return
     */
    public List<Song> findSongByMusician(Long musicianId){
        return em.createQuery("select s from Song s where s.musician.id = :musicianId",Song.class)
                .setParameter("musicianId",musicianId)
                .getResultList();
    }

    /**
     *
     * @param musicianId
     * @return
     */
    public Song findRPSongByMusician(Long musicianId){
        return em.createQuery("select s from Song s where s.musician.id = :musicianId and s.represent = 1",Song.class)
                .setParameter("musicianId",musicianId)
                .getSingleResult();
    }

    public void save(Song song){
        EntityManager em = this.em;
        em.persist(song);
    }
}
