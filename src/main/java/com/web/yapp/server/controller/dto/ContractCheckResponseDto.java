package com.web.yapp.server.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ContractCheckResponseDto {
    private String usage;
    private String copyright;
    private List<String> atmoList;
    private List<String> themeList;
    private List<String> genreList;
    private List<String> instruList;
    private List<String> spclList;
    private String minTime;
    private String maxTime;
    private String intention;
    private String phoneNumber;
    private String kakaoId;
    private String startDate;
    private String dueDate;
    private int minFee;
    private int maxFee;
}
