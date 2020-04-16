package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Musician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicianRepository extends JpaRepository<Musician, Long> {
}
