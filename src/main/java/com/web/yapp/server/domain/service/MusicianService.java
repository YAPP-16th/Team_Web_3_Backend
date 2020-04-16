package com.web.yapp.server.domain.service;


import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.repository.MusicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)     /* 전체 서비스를 Readonly로 처리 단, 회원가입의 경우만 트랜잭션 처리 */
@RequiredArgsConstructor
public class MusicianService {
    private final MusicianRepository musicianRepository;

    @Transactional
    public Long join(Musician musician){

        validateDuplicateMusician(musician);
        musicianRepository.save(musician);
        return musician.getId();
    }
    //중복 회원 체크
    private void validateDuplicateMusician(Musician musician) {
        // EXCEPTION
        List<Musician> findMembers = musicianRepository.findByName(musician.getName());           // 유니크 제약조건을 먹어주는게 동시회원가입을 막을 수 있다.

        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


}
