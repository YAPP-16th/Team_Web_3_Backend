package com.web.yapp.server.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContractTagDto {
    List<String> tags;
    String etcTag;
}
