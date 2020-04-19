package com.web.yapp.server.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    MUSICIAN("ROLE_MUSICIAN","뮤지션"),
    USER("ROLE_USER","유저");

    private final String key;
    private final String title;
}
