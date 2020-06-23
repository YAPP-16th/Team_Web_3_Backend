package com.web.yapp.server.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class ContractSaveRequestDto {
    private ContractDto contractDto;
    private ContractTagDto atmo;
    private ContractTagDto theme;
    private ContractTagDto genre;
    private ContractTagDto instru;
    private ContractTagDto spclNote;
    private List<MultipartFile> documents;
    private Long userId;
    private Long musicianId;
}
