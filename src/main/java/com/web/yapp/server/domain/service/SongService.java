package com.web.yapp.server.domain.service;

import com.web.yapp.server.controller.dto.SongDto;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.Song;
import com.web.yapp.server.domain.repository.MusicianRepository;
import com.web.yapp.server.domain.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SongService {
    private final MusicianRepository musicianRepository;
    private final SongRepository songRepository;
    private final S3Uploader s3Uploader;

    /** 뮤지션값 기준으로 노래 가져오기 => 뮤지션페이지에서 사용할듯?
     *
     * @param musicianId
     * @return
     */
    @Transactional(readOnly = true)
    public List<SongDto> findSongByMusicianId(Long musicianId){
        return songRepository.findSongByMusician(musicianId).stream()
                .map(SongDto::new)
                .collect(Collectors.toList());
    }

    /** 대표곡 조회
     *
     * @param musicianId
     * @return
     */
    @Transactional(readOnly = true)
    public SongDto findRPSongByMuscianId(Long musicianId){
        Song song = songRepository.findRPSongByMusician(musicianId);
        System.out.println("mid:"+musicianId);
        System.out.println("title:"+song.getTitle());
        return new SongDto(song);
    }

    /**
     *
     * @param multipartFiles
     * @param musicianId
     * @return
     * @throws IOException
     */
    @Transactional //대표곡커버, 대표곡, 일반곡1, ... 순서
    public List<Long> song(List<MultipartFile> multipartFiles, Long musicianId) throws IOException {
        List<Long> idList = new LinkedList<Long>();
        Musician musician = musicianRepository.findOne(musicianId);
        String RPcoverUrl = s3Uploader.upload(multipartFiles.get(0),"static");
        String RPsongUrl = s3Uploader.upload(multipartFiles.get(1),"static");
        String RPtitle = multipartFiles.get(1).getOriginalFilename();

        Song RPsong = Song.builder()
                .title(RPtitle)
                .coverUrl(RPcoverUrl)
                .songUrl(RPsongUrl)
                .represent(1)
                .musician(musician)
                .build();

        songRepository.save(RPsong);
        idList.add(RPsong.getId());

        for(int i=2;i<multipartFiles.size();i++){
            String title = multipartFiles.get(i).getOriginalFilename();
            String songUrl = s3Uploader.upload(multipartFiles.get(i),"static");
            Song song = new Song();
            song.builder()
                    .title(title)
                    .songUrl(songUrl)
                    .coverUrl("")
                    .represent(0)
                    .musician(musician)
                    .build();
        }
        return idList;
    }

}
