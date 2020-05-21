package com.web.yapp.server.controller;

//import com.web.yapp.server.controller.dto.OAuthAttributesDto;
//import com.web.yapp.server.controller.dto.SessionUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@CrossOrigin("*")
@Controller
@RequiredArgsConstructor
public class UserController {
    private final HttpSession httpSession;
    /**
     * 구글 로그인 성공시 화면 이동 유저 데이터 가져옴
     * @param model
     * @return
     */
    @GetMapping("/users/new")
    public String createForm(Model model){
        // 유저 세션 가져옴
//        SessionUserDto user = (SessionUserDto) httpSession.getAttribute("user");
//
//        // access token => JWT => return User = [{status:value, jwt : jwt}] fail: 0, 1: success
//        // 유저 세션이 NOT NULL
//        if(user != null) {
//            model.addAttribute("user", user.getName());
//
//
//            /* 우리가 앱에서 사용할 유저 모델을 생성해야한다. */
//
//
//
//        }
        // login.mustache
        return "redirect:/";
    }




}
