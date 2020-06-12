package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Contract;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ContractRequestDto {
    private String usage;
    private String copyright;
    private List<String> atmoList;
    private List<String> themeList;
    private List<String> genreList;
    private List<String> instruList;
    private List<String> spclList;
    private String minTime;
    private String maxTime;
    private String playTimeChangable;
    private String intention;
    private String phoneNumber;
    private String kakaoId;
    private String startDate;
    private String dueDate;
    private int minFee;
    private int maxFee;
    private String docuUrl1;
    private String docuUrl2;
    private String docuUrl3;
    private String docuUrl4;
    private String docuUrl5;

    public Contract toEntity(){
        return Contract
    }
}
