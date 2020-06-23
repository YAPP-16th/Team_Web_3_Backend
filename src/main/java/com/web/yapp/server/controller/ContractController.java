package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.ContractDto;
import com.web.yapp.server.controller.dto.ContractSaveRequestDto;
import com.web.yapp.server.controller.dto.ContractTagDto;
import com.web.yapp.server.domain.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class ContractController {
    private final ContractService contractService;

    @PostMapping("/contract")
    public void createContract(@RequestBody ContractSaveRequestDto contractSaveRequestDto) throws IOException {
        contractService.createContract(contractSaveRequestDto);
    }

}
