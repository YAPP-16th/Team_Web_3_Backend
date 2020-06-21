package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Song;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        try{
            return em.createQuery("select s from Song s where s.musician.id = :musicianId",Song.class)
                    .setParameter("musicianId",musicianId)
                    .getResultList();
        }catch (NoResultException e){
            log.error("SongRepository findSongByMusician :"+e.getMessage());
            return new ArrayList<Song>();
        }

    }

    /**
     *
     * @param musicianId
     * @return
     */
    public Song findRPSongByMusician(Long musicianId){
        try{
            return em.createQuery("select s from Song s where s.musician.id = :musicianId and s.represent = 1",Song.class)
                    .setParameter("musicianId",musicianId)
                    .getSingleResult();
        }catch (NoResultException e){
            log.error("SongRepository findRPSongByMusician :"+e.getMessage());
            return new Song(); //될지모르겠음
        }

    }

    public void save(Song song){
        EntityManager em = this.em;
        em.persist(song);
    }
}
