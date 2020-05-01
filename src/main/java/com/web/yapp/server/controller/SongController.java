package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.SongDto;
import com.web.yapp.server.domain.Song;
import com.web.yapp.server.domain.repository.SongRepository;
import com.web.yapp.server.domain.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/song")
public class SongController {
    private final SongRepository songRepository;
    private final S3Uploader s3Uploader;

    /**
     * 
     * @param songId
     * @return
     */
    @GetMapping("/{songId}") //노래 하나만 받아오기
    public Optional<Song> songById(@PathVariable Long songId){ //findbyid
        Optional<Song> songDto = songRepository.findById(songId);
        return songDto;
    }

    @GetMapping("/musician/{musicianId}") //뮤지션페이지에서 노래 목록 제공
    public List<SongDto> songByMusician(@PathVariable Long musicianId){

    }

    /*
    * 업로드하면 url반환도 해주고 song save도 될 수있도록 구현하기
    * */
//    @PostMapping //노래추가
//    public SongDto createSong(@RequestParam String title, @RequestParam String ){ //save
//        SongDto songDto = new SongDto();
//        songRepository.save(songDto.toEntity());
//        return songDto;
//    }

    // song 등록 api를 굳이 만들 필요 없음 service 통해서 파일만 업로드 해주면됨
//    @PostMapping("/represent") // 대표곡 업로드 API. 파일 URL 리턴
//    @ResponseBody
//    public SongDto upload(@RequestParam("data")  List<MultipartFile> multipartFiles, String title, Long musicainId) throws IOException {
//        String coverUrl = s3Uploader.upload(multipartFiles.get(0), "static");
//        String songUrl = s3Uploader.upload(multipartFiles.get(1),"static");
//        SongDto songDto = new SongDto();
//        songDto.builder()
//                .title(title)
//                .build();
//
//        return result;
//    }
//    @PutMapping
//    public SongDto updateSong(){} //save
//
//    @DeleteMapping
//    public SongDto deleteSong(){} //delete


}
