package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.SongDto;
import com.web.yapp.server.domain.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SongController {
    private final SongRepository songRepository;

    @PostMapping("/song")
    public SongDto createSong(SongDto dto){
        songRepository.save(dto.toEntity());
        return dto;
    }

}
