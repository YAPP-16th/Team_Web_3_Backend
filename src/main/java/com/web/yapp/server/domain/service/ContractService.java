package com.web.yapp.server.domain.service;

import com.web.yapp.server.controller.dto.ContractRequestDto;
import com.web.yapp.server.controller.dto.ContractTagDto;
import com.web.yapp.server.domain.*;
import com.web.yapp.server.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final UserClassRepository userClassRepository;
    private final MusicianRepository musicianRepository;
    private final S3Uploader s3Uploader;
    private final ContractTagService contractTagService;
    
    public void createContract(ContractRequestDto contractRequestDto, ContractTagDto atmo, ContractTagDto theme,
                               ContractTagDto genre, ContractTagDto instru, ContractTagDto spclNote,
                               List<MultipartFile> documents, Long userId,  Long musicianId) throws IOException {
        Musician musician = musicianRepository.findOne(musicianId);
        User user = userClassRepository.findUserById(userId);

        List<String> docuUrls = getUrl(documents);

        Contract contract = contractRequestDto.toEntity(musician, user, "대기", docuUrls);
        contractRepository.save(contract);

        contractTagService.saveContractTag(atmo, contract, "분위기");
        contractTagService.saveContractTag(theme, contract, "테마");
        contractTagService.saveContractTag(genre, contract, "장르");
        contractTagService.saveContractTag(instru, contract, "악기");
        contractTagService.saveContractTag(spclNote, contract, "작업");
    }

    public List<String> getUrl(List<MultipartFile> documents) throws IOException {
        List<String> docuUrls = new LinkedList<String>();
        for (MultipartFile file: documents
        ) {
            docuUrls.add(s3Uploader.upload(file,"static"));
        }
        return docuUrls;
    }


}
