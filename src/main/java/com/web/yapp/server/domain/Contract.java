package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_CONTRACT")
@Entity
public class Contract extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTRACT_ID")
    private Long id;

    @ManyToOne // 의뢰서(n) : 뮤지션(1)
    @JoinColumn(name = "MUSICIAN_ID")
    private Musician musician;

    @ManyToOne // 의뢰서(n) : 유저(1)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "CONTRACT_USAGE")
    private String usage;

    @Column(name = "CONTRACT_COPYRIGHT")
    private String copyright;

    @Column(name = "CONTRACT_INTENTION")
    private String intention;

    @Column(name = "CONTRACT_MIN_FEE")
    private int minFee;

    @Column(name = "CONTRACT_MAX_FEE")
    private int maxFee;

    @Column(name = "CONTRACT_PLAY_MIN_TIME")
    private String minTime;

    @Column(name = "CONTRACT_PALY_MAX_TIME")
    private String maxTime;

    @Column(name = "CONTRACT_PLAY_TIME_CHANGEABLE")
    private String playTimeChangable;

    /*
    1. 뮤지션수락
    2. 뮤지션거절
    3. 유저(의뢰취소)
     */
    @Column(name = "CONTRACT_STATUS")
    private String status; //의뢰상태

    @Column(name = "CONTRACT_START_DATE")
    private String startDate;

    @Column(name = "CONTRACT_DUE_DATE")
    private String dueDate;

    @Column(name = "CONTRACT_KAKAO_ID")
    private String kakaoId;

    @Column(name = "CONTRACT_PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "CONTRACT_DOCUMENT_URL1")
    private String docuUrl1;

    @Column(name = "CONTRACT_DOCUMENT_URL2")
    private String docuUrl2;

    @Column(name = "CONTRACT_DOCUMENT_URL3")
    private String docuUrl3;

    @Column(name = "CONTRACT_DOCUMENT_URL4")
    private String docuUrl4;

    @Column(name = "CONTRACT_DOCUMENT_URL5")
    private String docuUrl5;

    @Builder
    public Contract(User user, Musician musician, String usage, String copyright, String intention,
                    int minFee, int maxFee, String minTime, String maxTime, String playTimeChangable, String status, String startDate,
                    String dueDate, String kakaoId, String phoneNumber, String docuUrl1, String docuUrl2, String docuUrl3, String docuUrl4, String docuUrl5){
        this.user = user;
        this.musician = musician;
        this.usage = usage;
        this.copyright = copyright;
        this.intention = intention;
        this.minFee = minFee;
        this.maxFee = maxFee;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.playTimeChangable = playTimeChangable;
        this.status = status;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.kakaoId = kakaoId;
        this.phoneNumber = phoneNumber;
        this.docuUrl1 = docuUrl1;
        this.docuUrl2 = docuUrl2;
        this.docuUrl3 = docuUrl3;
        this.docuUrl4 = docuUrl4;
        this.docuUrl5 = docuUrl5;
    }

}
