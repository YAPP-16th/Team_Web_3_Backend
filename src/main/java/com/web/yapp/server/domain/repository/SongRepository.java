package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAll();
    Optional<Song> findById(Long id);
    List<Song> findByMusicianId(Long id);
}
