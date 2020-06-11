package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.ContractDto;
import com.web.yapp.server.domain.Tag;
import com.web.yapp.server.domain.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContractController {
    private final ContractService contractService;

    @GetMapping("/contract")
    public void createContract(ContractDto contractDto, List<String> atmoList, List<String> themeList,
                               List<String> instruList, List<String> genreList, List<String> spclNoteList){
        //contract, contracttag 둘다 save 필요

    }
}
