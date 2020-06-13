package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.ContractRequestDto;
import com.web.yapp.server.domain.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContractController {
    private final ContractService contractService;

    @GetMapping("/contract")
    public void createContract(@RequestParam ContractRequestDto contractRequestDto,
                               @RequestParam List<String> atmoList, @RequestParam List<String> themeList,
                               @RequestParam List<String> instruList, @RequestParam List<String> genreList,
                               @RequestParam List<String> spclNoteList, @RequestParam List<MultipartFile> documents,
                               @RequestParam Long userId, @RequestParam Long musicianId){

        //contract, contracttag 둘다 save 필요
        contractService.createContract(contractRequestDto, atmoList, themeList,
                instruList, genreList, spclNoteList, userId, musicianId);
    }
}
