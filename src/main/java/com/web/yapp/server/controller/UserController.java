package com.web.yapp.server.controller;

import com.web.yapp.server.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        // 유저 세션이 NOT NULL
        if(user != null) {
            model.addAttribute("user", user.getName());
        }
        // login.mustache
        return "redirect:/";
    }




}
