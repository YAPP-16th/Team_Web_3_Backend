package com.web.yapp.server.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ContractDto {
    private String usage;
    private String copyright;
    private String intention;
    private String musicLength;
    private int minFee;
    private int maxFee;
    private int status; //의뢰상태
    private String deadline;
    private String docuUrl1;
    private String docuUrl2;
    private String docuUrl3;
    private String docuUrl4;
    private String docuUrl5;
}
