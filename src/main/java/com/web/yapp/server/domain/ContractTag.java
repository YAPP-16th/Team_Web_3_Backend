package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "TUNA_CON_TAG_MAP")
@Entity
public class ContractTag extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CONTRACT_ID")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    // "Y" or "N"
    @Column(name = "CON_TAG_MAP_RPRSN")
    private String represent;

    @Column(name = "CON_TAG_CATEGORY_NM")
    private String categoryNM;

    @Builder
    public ContractTag(Contract contract, Tag tag, String represent, String categoryNM){
        this.contract = contract;
        this.tag = tag;
        this.represent = represent;
        this.categoryNM = categoryNM;
    }

}
