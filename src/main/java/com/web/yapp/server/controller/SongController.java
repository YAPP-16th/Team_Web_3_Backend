package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.SongDto;
import com.web.yapp.server.domain.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/song")
public class SongController {
    private final SongService songService;

    /**
     *
     * @param musicianId
     * @return
     */
    @GetMapping("/musician/{musicianId}") //뮤지션페이지에서 노래 목록 제공
    public List<SongDto> songsByMusician(@PathVariable Long musicianId){
        return songService.findSongByMusicianId(musicianId);
    }

//    @PutMapping
//    public SongDto updateSong(){} //save
//
//    @DeleteMapping
//    public SongDto deleteSong(){} //delete
    
}
