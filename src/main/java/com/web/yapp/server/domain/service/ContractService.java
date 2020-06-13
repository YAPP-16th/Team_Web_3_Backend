package com.web.yapp.server.domain.service;

import com.web.yapp.server.controller.dto.ContractRequestDto;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.User;
import com.web.yapp.server.domain.repository.ContractRepository;
import com.web.yapp.server.domain.repository.ContractTagRepository;
import com.web.yapp.server.domain.repository.MusicianRepository;
import com.web.yapp.server.domain.repository.UserClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final UserClassRepository userClassRepository;
    private final MusicianRepository musicianRepository;
    private final ContractTagRepository contractTagRepository;
/*
* @RequestParam ContractRequestDto contractRequestDto,
                               @RequestParam List<String> atmoList, @RequestParam List<String> themeList,
                               @RequestParam List<String> instruList, @RequestParam List<String> genreList,
                               @RequestParam List<String> spclNoteList, @RequestParam List<MultipartFile> documents,
                               @RequestParam Long userId, @RequestParam Long musicianId
* */
    public void createContract(ContractRequestDto contractRequestDto, List<String> atmoList, List<String> themeList,
                               List<String> instruList, List<String> genreList, List<String> spclNoteList, Long userId, Long musicianId){
        Musician musician = musicianRepository.findOne(musicianId);
        User user = userClassRepository.findUserById(userId);
//        Contract contract = contractRequestDto.toEntity(musician, user, "대기");
//        contractRepository.save(contract);
        //태그 map 저장
    }
}
