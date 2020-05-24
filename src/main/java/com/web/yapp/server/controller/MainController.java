package com.web.yapp.server.controller;




//import com.oracle.tools.packager.Log;
import com.web.yapp.server.controller.dto.SessionUserDto;
import com.web.yapp.server.domain.Role;
import com.web.yapp.server.domain.User;
import com.web.yapp.server.domain.repository.UserRepository;
import com.web.yapp.server.domain.service.CategoryService;
import com.web.yapp.server.domain.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * TUNA MAIN 컨트롤러
 */
@CrossOrigin("*")
@RequiredArgsConstructor
@Controller
@Slf4j      /* 로그 어노테이션 */
public class MainController {
    private final HttpSession httpSession;
//    private final CustomOAuth2UserService customOAuth2UserService;

    private final CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model) {       // 모델에 유저 정보


//        SessionUserDto user = (SessionUserDto) httpSession.getAttribute("user");


        /**
         * 뮤지션 카테고리별 큐레이션 조회
         * 1. 큐레이션 값 저장
         * 2. 큐레이션 값 조회
         * 3. 해당되는 뮤지션 큐레이션 조건으로 조회 데이터
         */

        log.info(String.valueOf(model));
        /**
         * 유저 세션 정보 유무 확인
         */

//        if(user != null){
//            if(user.getRole().getKey() == "ROLE_MUSICIAN"){
//                // 뮤지션등록하고 나서 이제 메인에서 로그인 뮤지션세팅해줘야하잖음
//            }
//
//            if(user.getRole().getKey() == "ROLE_USER"){
//                // 최초 진입
//
//            }
//
//            Log.info(String.valueOf(user));
//
//
//
//            /* 비즈니스로직 여기서 처리 데이터 로딩, 세션 처리 완료 로직 처리 */
//
//127.0.0.1:8080/oauth2/authorization/google
//
//        }else {
//            return "redirect:/users/new";
//        }

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


        log.info("메인 컨트롤러 ");
        return "redirect:ec2-13-209-105-111.ap-northeast-2.compute.amazonaws.com:3000";
    }



}