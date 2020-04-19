package com.web.yapp.server.controller;



import com.oracle.tools.packager.Log;
import com.sun.media.jfxmedia.logging.Logger;
import com.web.yapp.server.config.auth.dto.SessionUser;
import com.web.yapp.server.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * TUNA MAIN 컨트롤러
 */
@RequiredArgsConstructor
@Controller
@Slf4j      /* 로그 어노테이션 */
public class MainController {
    @GetMapping("/")
    public String home(Model model) {       // 모델에 유저 정보


        if(model != null){



            Log.info(String.valueOf(model));

        }


        /*유저 정보에 담긴 값들을 바탕으로 Userservice생성해서 repository와 함께 값을 save시켜버리면 되겠다. */

        /**
         * 1. 뮤지션 정보들 로딩 시켜야함.
         * 2. 리스너들의 선택, 등장 새로운 뮤지션 정렬 및 필터
         * 3. 뮤지션 프로필, 뮤지션 이름, 뮤지션 소개, 좋아요 개수, 뮤지션 취향 카테고리
         * 4. 뮤지션 보기 클릭시 -> 뮤지션 보기 페이지로 이동
         * 5. 뮤지션 노래 클릭시 -> 음악 재생
         * 6. 뮤지션 음악 파일 로딩해놔야함
         * 7. 클라이언트에서 페이지네이션 처리할때 페이지 번호를 넘겨준다고 하였으니까 우리가 페이지 넘버에 따라 1페이지 3명,6명 .... 이렇게 값을 넘겨주어야함.
         *
         */



        return "home";
    }


}