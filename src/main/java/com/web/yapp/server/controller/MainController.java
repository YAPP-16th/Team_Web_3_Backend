package com.web.yapp.server.controller;



import com.web.yapp.server.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 *
 */
@RequiredArgsConstructor
@Controller
@Slf4j      /* 로그 어노테이션 */
public class MainController {
    @GetMapping("/")
    public String home(Model model) {


        /**
         * 뮤지션 정보 추가
         */

        return "home";
    }


}