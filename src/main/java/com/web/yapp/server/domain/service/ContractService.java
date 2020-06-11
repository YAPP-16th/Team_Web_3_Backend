package com.web.yapp.server.domain.service;

import com.web.yapp.server.domain.Contract;
import com.web.yapp.server.domain.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContractService {
    private final ContractRepository contractRepository;

//    public void createContract(){
//        Contract contract = Contract
//                //.buider
//        contractRepository.save(contract);
//    }
}
