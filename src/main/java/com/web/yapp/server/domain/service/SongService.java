package com.web.yapp.server.domain.service;

import com.web.yapp.server.controller.dto.SongDto;
import com.web.yapp.server.domain.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SongService {
    private final SongRepository songRepository;

    public List<SongDto> findSongByMusicianId(Long musicianId){

    }
}
