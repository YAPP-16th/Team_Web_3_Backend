package com.web.yapp.server.config.auth.dto;

import com.web.yapp.server.domain.Role;
import com.web.yapp.server.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String profile_url;

    @Builder
    public OAuthAttributes(Map<String,Object> attributes,
                           String nameAttributeKey, String name,
                           String email, String profile_url){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.profile_url = profile_url;
    }

    //registrationId에 따른 메서드 호출하는 역할
    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                     Map<String,Object> attributes){
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .profile_url((String)attributes.get("picture")) //구글에서 주는 picture 값 가져오기. oAuth2User.attributes.picture
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    //private static OAuthAttributes ofNaver(){}

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .profile_url(profile_url)
                .role(Role.USER)
                .build();
    }


}
