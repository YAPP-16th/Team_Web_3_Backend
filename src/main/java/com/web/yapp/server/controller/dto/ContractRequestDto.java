package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Contract;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ContractRequestDto {
    private String usage;
    private String copyright;
//    private List<String> atmoList;
//    private List<String> themeList;
//    private List<String> genreList;
//    private List<String> instruList;
//    private List<String> spclList;
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

    public Contract toEntity(Musician musician, User user, String status, List<String> documentUrl){
        return Contract.builder()
                .user(user)
                .musician(musician)
                .usage(usage)
                .copyright(copyright)
                .minTime(minTime)
                .maxTime(maxTime)
                .playTimeChangable(playTimeChangable)
                .minFee(minFee)
                .maxFee(maxFee)
                .intention(intention)
                .phoneNumber(phoneNumber)
                .kakaoId(kakaoId)
                .startDate(startDate)
                .dueDate(dueDate)
                .status(status)
                .build();
    }

    public ContractRequestDto(Contract Entity){
        this.usage = Entity.getUsage();
        this.copyright = Entity.getCopyright();
        this.minTime = Entity.getMinTime();
        this.maxTime = Entity.getMaxTime();
        this.playTimeChangable = Entity.getPlayTimeChangable();
        this.minFee = Entity.getMinFee();
        this.maxFee = Entity.getMaxFee();
        this.startDate = Entity.getStartDate();
        this.dueDate = Entity.getDueDate();
        this.kakaoId = Entity.getKakaoId();
        this.phoneNumber = Entity.getPhoneNumber();
    }
}
