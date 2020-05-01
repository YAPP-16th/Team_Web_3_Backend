package com.web.yapp.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "TUNA_MUSICIAN")
@Entity
public class Musician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MUSICIAN_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

    @Column(name = "MUSICIAN_NICKNAME", nullable = false)        // 1Step 뮤지션 닉네임
    private String nickNm;

    @Column(name = "MUSICIAN_INTRODUCTION", nullable = false) // 1step 뮤지션 프로필 문구
    private String introduction;

    @Column(name = "MUSICIAN_CAREER") // 1step 뮤지션 경력 경력
    private String career;

    @Column(name = "MUSICIAN_CELPHN") // 1step 뮤지션 핸드폰번호
    private String celPhone;

    @Column(name = "MUSICIAN_PORT_LINK")    // 1step 뮤지션 포트폴리오 링크
    private String portFolioLink;

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

    @Column(name = "MUSICIAN_QSTN_ANS05")       //step02 Q&A 05
    private String qstnAns05;

    @Column(name = "MUSICIAN_PRC01")            //step03 스타일 A 가격 01
    private Long styPrc01;

    @Column(name = "MUSICIAN_PRC02")            //step03 스타일 B 가격 02
    private Long styPrc02;

    @Column(name = "MUSICIAN_PRC03")            //step03 스타일 C 가격 03
    private Long styPrc03;

    @Column(name = "MUSICIAN_EXPLN01")          //step03 스타일 A 설명 01
    private String styExpln01;

    @Column(name = "MUSICIAN_EXPLN02")          //step03 스타일 B 설명 02
    private String styExpln02;

    @Column(name = "MUSICIAN_EXPLN03")          //step03 스타일 C 설명 03
    private String styExpln03;

    @Column(name = "MUSICIAN_PROFILE_URL") //프로필 사진 url
    private String profileUrl;

}
