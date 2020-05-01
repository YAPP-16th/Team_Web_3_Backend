package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Musician;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class MusicianDto {

    @NotEmpty(message = "뮤지션 경력은 필수입니다.")
    private String career;
    @NotEmpty(message = "뮤지션 이름은 필수입니다.")
    private String nickNm;
    @NotEmpty(message = "뮤지션 소개는 필수입니다.")
    private String introduction;

    private String celPhone;
    private String portFolioLink;
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
    private Long styPrc01;
    private Long styPrc02;
    private Long styPrc03;
    private String styExpln01;
    private String styExpln02;
    private String styExpln03;
    private String profileUrl;
    @Builder
    public MusicianDto(@NotEmpty(message = "뮤지션 경력은 필수입니다.") String career,
                       @NotEmpty(message = "뮤지션 이름은 필수입니다.") String nickNm,
                       @NotEmpty(message = "뮤지션 소개는 필수입니다.") String introduction,
                       String celPhone,
                       String portFolioLink,
                       String snsNm,
                       Long snsType,
                       String workStage01,
                       String workStage02,
                       String workStage03,
                       String qstnAns01,
                       String qstnAns02,
                       String qstnAns03,
                       String qstnAns04,
                       String qstnAns05,
                       Long styPrc01,
                       Long styPrc02,
                       Long styPrc03,
                       String styExpln01,
                       String styExpln02,
                       String styExpln03,
                       String profileUrl){
        this.career = career;
        this.nickNm = nickNm;
        this.introduction = introduction;
        this.celPhone = celPhone;
        this.portFolioLink = portFolioLink;
        this.snsNm = snsNm;
        this.snsType = snsType;
        this.workStage01 = workStage01;
        this.workStage02 = workStage02;
        this.workStage03 = workStage03;
        this.qstnAns01 = qstnAns01;
        this.qstnAns02 = qstnAns02;
        this.qstnAns03 = qstnAns03;
        this.qstnAns04 = qstnAns04;
        this.qstnAns05 = qstnAns05;
        this.styPrc01 = styPrc01;
        this.styPrc02 = styPrc02;
        this.styPrc03 = styPrc03;
        this.styExpln01 = styExpln01;
        this.styExpln02 = styExpln02;
        this.styExpln03 = styExpln03;
        this.profileUrl = profileUrl;

    }

    public Musician toEntity(){
        return Musician.builder()
                .career(career)
                .nickNm(nickNm)
                .introduction(introduction)
                .celPhone(celPhone)
                .portFolioLink(portFolioLink)
                .snsNm(snsNm)
                .snsType(snsType)
                .workStage01(workStage01)
                .workStage02(workStage02)
                .workStage03(workStage03)
                .qstnAns01(qstnAns01)
                .qstnAns02(qstnAns02)
                .qstnAns03(qstnAns03)
                .qstnAns04(qstnAns04)
                .qstnAns05(qstnAns05)
                .styPrc01(styPrc01)
                .styPrc02(styPrc02)
                .styPrc03(styPrc03)
                .styExpln01(styExpln01)
                .styExpln02(styExpln02)
                .styExpln03(styExpln03)
                .profileUrl(profileUrl)
                .build();
    }
}
