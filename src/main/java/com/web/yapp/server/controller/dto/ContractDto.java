package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Contract;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class ContractDto {
    private String usage;
    private String copyright;
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
        //list의 원소가 비어있을 경우 고려해야함
        String[] urlSetting = new String[5];
        urlSetting[0] = "";
        urlSetting[1] = "";
        urlSetting[2] = "";
        urlSetting[3] = "";
        urlSetting[4] = "";

        for(int i=0;i<documentUrl.size();i++){
            urlSetting[i] = documentUrl.get(i);
        }
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
                .docuUrl1(urlSetting[0])
                .docuUrl2(urlSetting[1])
                .docuUrl3(urlSetting[2])
                .docuUrl4(urlSetting[3])
                .docuUrl5(urlSetting[4])
                .build();
    }

//    public ContractRequestDto(Contract Entity){
//        this.usage = Entity.getUsage();
//        this.copyright = Entity.getCopyright();
//        this.minTime = Entity.getMinTime();
//        this.maxTime = Entity.getMaxTime();
//        this.playTimeChangable = Entity.getPlayTimeChangable();
//        this.minFee = Entity.getMinFee();
//        this.maxFee = Entity.getMaxFee();
//        this.startDate = Entity.getStartDate();
//        this.dueDate = Entity.getDueDate();
//        this.kakaoId = Entity.getKakaoId();
//        this.phoneNumber = Entity.getPhoneNumber();
//    }
}
