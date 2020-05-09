package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
