package com.web.yapp.server.domain.service;


import com.web.yapp.server.controller.dto.MusicianDto;
import com.web.yapp.server.controller.dto.SessionUserDto;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.repository.MusicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)     /* 전체 서비스를 Readonly로 처리 단, 회원가입의 경우만 트랜잭션 처리 */
@RequiredArgsConstructor
public class MusicianService {
    private final MusicianRepository musicianRepository;

    @Transactional
    public Long join(MusicianDto musicianDto){
        Musician musician = new Musician();
        /*
        빌더패턴 적용
         */
//        Musician.MusicianBuilder builder = Musician.builder();
//        builder.career(musicianDto.getCareer())
//                .nickNm(musicianDto.getNickNm())
//                .introduction(musicianDto.getIntroduction())
//                .profileUrl(musicianDto.getProfileUrl())
//                .build();

//        validateDuplicateMusician(musician);

        musician = musicianDto.toEntity();
        musicianRepository.save(musician);
        return musician.getId();
    }
    //중복 회원 체크
    private void validateDuplicateMusician(Musician musician) {
        // EXCEPTION
        List<Musician> findMusicians = musicianRepository.findByNickNm(musician.getNickNm());           // 유니크 제약조건을 먹어주는게 동시회원가입을 막을 수 있다.

        if(!findMusicians.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<MusicianDto> findAllMusician(){
        return musicianRepository.findAllMusician().stream()
                .map(MusicianDto::new)
                .collect(Collectors.toList());

    }

    public MusicianDto findByIdMusician(Long id){
        return new MusicianDto(musicianRepository.findOne(id));
    }


    public List<MusicianDto> findByNickNmMusician(String nickNm) {
        return musicianRepository.findByNickNm(nickNm).stream()
                .map(MusicianDto::new)
                .collect(Collectors.toList());
    }
}
