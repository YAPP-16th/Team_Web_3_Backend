package com.web.yapp.server.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Setter
@Getter
public class CurationReqDto {
    private List<String> atmoList;
    private List<String> genreList;
    private List<String> instruList;
    private List<String> themeList;
}
