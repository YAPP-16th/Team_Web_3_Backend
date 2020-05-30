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

    private String copyright;

    //private

}
