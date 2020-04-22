package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findById(Long id);
    //    @Query("SELECT s FROM Song s where ")
//    List<Song> findByMusicianId(Long id);
}
