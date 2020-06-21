package com.web.yapp.server.domain.service;

import com.web.yapp.server.controller.dto.ContractTagDto;
import com.web.yapp.server.domain.Contract;
import com.web.yapp.server.domain.ContractTag;
import com.web.yapp.server.domain.Tag;
import com.web.yapp.server.domain.repository.ContractTagRepository;
import com.web.yapp.server.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ContractTagService {
    private final TagRepository tagRepository;
    private final ContractTagRepository contractTagRepository;

    public void saveContractTag(ContractTagDto contractTagDto, Contract contract, String categoryNM){
        List<String> tagList = contractTagDto.getTags();
        if(!(contractTagDto.getEtcTag().equals(""))) tagList.add(contractTagDto.getEtcTag());
        for(int i=0;i<tagList.size();i++){
            String tagNM = tagList.get(i);
            Tag tag = tagRepository.findTagByTagNM(tagNM);
            ContractTag contractTag;

            contractTag = ContractTag.builder()
                    .contract(contract)
                    .tag(tag)
                    .represent("N")
                    .categoryNM(categoryNM)
                    .build();

            if(i==0)  { //대표태그
                contractTag = ContractTag.builder()
                        .contract(contract)
                        .tag(tag)
                        .represent("Y")
                        .categoryNM(categoryNM)
                        .build();
            }
            contractTagRepository.save(contractTag);
        }
    }
}
