package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AtmosphereDto {
    List<String> tagNM;
}