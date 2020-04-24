package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.SongDto;
import com.web.yapp.server.domain.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/song")
public class SongController {
    private final SongRepository songRepository;

    @GetMapping("/{songId}")
    public SongDto songById(@PathVariable Long songId){
        return;
    }

    @GetMapping("/musician/{musicianId}")
    public List<SongDto> songByMusician(@PathVariable Long musicianId){

    }

    //리스너들의 선택

    //신규 뮤지션


    /*
    * 업로드하면 url반환도 해주고 song save도 될 수있도록 구현하기
    * */
    @PostMapping
    public SongDto createSong(SongDto dto){
        songRepository.save(dto.toEntity());
        return dto;
    }

    @PutMapping
    public SongDto updateSong(){}

    @DeleteMapping
    public SongDto deleteSong(){}


}
