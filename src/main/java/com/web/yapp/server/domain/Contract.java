package com.web.yapp.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_CONTRACT")
@Entity
public class Contract {
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

    @Column(name = "CONTRACT_MUSIC_LENGTH")
    private String musicLength;

    @Column(name = "CONTRACT_MIN_FEE")
    private int minFee;

    @Column(name = "CONTRACT_MAX_FEE")
    private int maxFee;

    @Column(name = "CONTRACT_STATUS")
    private int status; //의뢰상태

    @Column(name = "CONTRACT_DEADLINE")
    private String deadline;

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

}
