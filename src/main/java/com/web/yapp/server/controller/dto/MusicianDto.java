package com.web.yapp.server.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MusicianDto {

    @NotEmpty(message = "뮤지션 경력은 필수입니다.")
    private String career;
    @NotEmpty(message = "뮤지션 이름은 필수입니다.")
    private String nickNm;
    @NotEmpty(message = "뮤지션 소개는 필수입니다.")
    private String introduction;

    private String celPhone;
    private String portfolio;
    private String snsNm;
    private Long snsType;
    private String workStage01;
    private String workStage02;
    private String workStage03;
    private String qstnAns01;
    private String qstnAns02;
    private String qstnAns03;
    private String qstnAns04;
    private String qstnAns05;
    private long styPrc01;
    private long styPrc02;
    private long styPrc03;
    private String styExpln01;
    private String styExpln02;
    private String styExpln03;
    private String profileUrl;
}
