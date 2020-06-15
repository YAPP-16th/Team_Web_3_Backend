package com.web.yapp.server.domain.service;

import com.web.yapp.server.controller.dto.OAuthAttributesDto;
import com.web.yapp.server.controller.dto.SessionUserDto;
import com.web.yapp.server.domain.User;
import com.web.yapp.server.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final HttpSession httpSession;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 현재 로그인 진행 중인 서비스를 구분하는 코드. 이후 네이버 로그인 연동 시 네이버 로그인인지, 구글로그인인지 구분하기 위해 사용
        String userNameAttributeName = userRequest.
                getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        /*OAuth2 로그인 진행 시 키가 되는 필드값. Primary Key와 같은 의미
        구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오 등은 기본 지원하지 않음. 구글의 기본 코드는 "sub"
        네이버 로그인과 구글 로그인을 동시 지원할 때 사용됨
        */

        //response.addHeader("accessToken",userRequest.getAccessToken().getTokenValue());
        OAuthAttributesDto attributes = OAuthAttributesDto.of(registrationId, userNameAttributeName,
                oAuth2User.getAttributes());
        //OAuthAtrributes : OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
        //이후 네이버 등 다른 소셜 로그인도 이 클래스 사용

        User user = saveOrupdate(attributes);

        /* 유저 테이블 저장 */

        httpSession.setAttribute("user", new SessionUserDto(user)); //세션에 사용자 정보 저장
        httpSession.setAttribute("accessToken", userRequest.getAccessToken().getTokenValue()); //세션에 사용자 정보 저장



        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }
    private User saveOrupdate(OAuthAttributesDto attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(),
                        attributes.getProfile_url())
                )
                .orElse(attributes.toEntity());
//        Long userId = userService.createUser(attributes.getEmail(),attributes.getName(),attributes.getProfile_url());
//        return userService.findUserById(userId);
        return userRepository.save(user);
    }


}
