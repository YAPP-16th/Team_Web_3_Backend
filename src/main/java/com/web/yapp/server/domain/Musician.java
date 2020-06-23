package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_MUSICIAN")
@Entity
public class Musician{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MUSICIAN_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

    @Column(name = "MUSICIAN_NICKNAME")        // 1Step 뮤지션 닉네임
    private String nickNm;

    @Column(name = "MUSICIAN_PROFILE_URL")      //1Step 프로필 사진 url
    private String profileUrl;

    @Column(name = "MUSICIAN_INTRODUCTION") // 1step 뮤지션 프로필 문구
    private String introduction;

    @Column(name = "MUSICIAN_CAREER") // 1step 뮤지션 경력 경력
    private String career;

    @Column(name = "MUSICIAN_PORT_LINK")    // 1step 뮤지션 포트폴리오 링크
    private String portFolioLink;

    @Column(name = "MUSICIAN_CELLPHN") // 1step 뮤지션 핸드폰번호
    private String cellPhone;

    @Column(name = "MUSICIAN_SNS_NM")   // 1step 뮤지션 SNS 이름
    private String snsNm;

    @Column(name = "MUSICIAN_SNS_TYPE")  // 1step 뮤지션 SNS 타입 1: 카카오톡 2: 페이스북 3: 인스타그램 4: 트위터
    private Long snsType;

    /*
    포트폴리오 업로드 1 필수  -> 파일업로드에서 처리
    포트폴리오 업로드 2 선택  -> 파일업로드에서 처리
    특이사항 / 장르 / 분위기 / 악기    -> 각 카테고리별 테이블에서 처리
     */

    @Column(name = "MUSICIAN_WORK_STEP01")      //step02 작업단계 01
    private String workStage01;

    @Column(name = "MUSICIAN_WORK_STEP02")      //step02 작업단계 02
    private String workStage02;

    @Column(name = "MUSICIAN_WORK_STEP03")      //step02 작업단계 03
    private String workStage03;

    @Column(name = "MUSICIAN_QSTN_ANS01")       //step02 Q&A 01
    private String qstnAns01;

    @Column(name = "MUSICIAN_QSTN_ANS02")       //step02 Q&A 02
    private String qstnAns02;

    @Column(name = "MUSICIAN_QSTN_ANS03")       //step02 Q&A 03
    private String qstnAns03;

    @Column(name = "MUSICIAN_QSTN_ANS04")       //step02 Q&A 04
    private String qstnAns04;

    @Column(name = "MUSICIAN_QSTN_ANS05")       //step02 Q&A 04
    private String qstnAns05;

    @Column(name = "MUSICIAN_PRC_YN")           //step03 가격고정(Y) or 가격협의(N)
    private String styPrcYN;

    @Column(name = "MUSICIAN_PRC01")            //step03 옵션 A 가격 01
    private Long styPrc01;

    @Column(name = "MUSICIAN_PRC02")            //step03 옵션 B 가격 02
    private Long styPrc02;

    @Column(name = "MUSICIAN_PRC03")            //step03 옵션 C 가격 03
    private Long styPrc03;

    @Column(name = "MUSICIAN_EXPLN01")          //step03 옵션 A 설명 01
    private String styExpln01;

    @Column(name = "MUSICIAN_EXPLN02")          //step03 옵션 B 설명 02
    private String styExpln02;

    @Column(name = "MUSICIAN_EXPLN03")          //step03 옵션 C 설명 03
    private String styExpln03;

    @Column(name = "MUSICIAN_BOOKMARK_COUNT")   //이 뮤지션이 좋아요 받은 개수
    private Long bookmarkCount;

    @Builder
    public Musician(Long id,
                       User userId,
                       @NotEmpty(message = "뮤지션 경력은 필수입니다.") String career,
                       @NotEmpty(message = "뮤지션 이름은 필수입니다.") String nickNm,
                       @NotEmpty(message = "뮤지션 소개는 필수입니다.") String introduction,
                       String cellPhone,
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
                       String styPrcYN,
                       Long styPrc01,
                       Long styPrc02,
                       Long styPrc03,
                       String styExpln01,
                       String styExpln02,
                       String styExpln03,
                       String profileUrl,
                    Long bookmarkCount){
        this.id = id;
        this.userId = userId;
        this.career = career;
        this.nickNm = nickNm;
        this.profileUrl = profileUrl;
        this.introduction = introduction;
        this.cellPhone = cellPhone;
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
        this.qstnAns04 = qstnAns05;
        this.styPrcYN = styPrcYN;
        this.styPrc01 = styPrc01;
        this.styPrc02 = styPrc02;
        this.styPrc03 = styPrc03;
        this.styExpln01 = styExpln01;
        this.styExpln02 = styExpln02;
        this.styExpln03 = styExpln03;
        this.bookmarkCount = bookmarkCount;
    }
}
