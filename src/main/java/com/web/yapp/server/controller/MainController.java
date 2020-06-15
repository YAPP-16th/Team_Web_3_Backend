package com.web.yapp.server.controller;


//import com.oracle.tools.packager.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.yapp.server.controller.dto.SessionUserDto;
import com.web.yapp.server.domain.Role;
import com.web.yapp.server.domain.User;
import com.web.yapp.server.domain.service.MusicianService;
import com.web.yapp.server.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * TUNA MAIN 컨트롤러
 */
@CrossOrigin("*")
@SessionAttributes
@RequiredArgsConstructor
@Controller
@Slf4j      /* 로그 어노테이션 */
public class MainController {
    private final HttpSession httpSession;
    private final UserService userService;
    private final MusicianService musicianService;

    @GetMapping("/")
    public String home(Model model, HttpSession session, HttpServletResponse response) {       // 모델에 유저 정보

        List<HashMap<String, Object>> accessTokenList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        SessionUserDto user = (SessionUserDto) httpSession.getAttribute("user");
        String accessToken = (String) httpSession.getAttribute("accessToken");

        // 토큰이 있는 경우(사용자 정보랑 값 넣어서 리턴)
        if (accessToken != null) {


        } else {

        }

        //System.out.println("accessToken => " + obj);

//        accessTokenList.addAll(obj);
        /*ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(obj);
        System.out.println(jsonString);*/


        //log.info(String.valueOf(accessTokenListMap));
/*
        if(!"".equals(obj.get("tokenValue"))){
            log.info(String.valueOf(obj.get("tokenValue")));
        }*/
        /*if(jsonString.){

        }*/

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

        session.setAttribute("login", "locallogin");
        session.setAttribute("user", user);
        httpSession.setAttribute("user", user);
        log.info("메인 컨트롤러 ");
       /* Cookie cookie = new Cookie("user", user);
        cookie.setValue(user);
        cookie.setMaxAge(3600); // 쿠키 유효기간 설정 (초 단위)
        response.addCookie(cookie);*/
//        model.addAttribute("httpSession", httpSession);

        //return "redirect:http://ec2-13-209-105-111.ap-northeast-2.compute.amazonaws.com:3000";

        Cookie cookie = new Cookie("accessToken", accessToken);
        cookie.setMaxAge(3600); // 쿠키 유효기간 설정 (초 단위)
        response.addCookie(cookie);

        //return "redirect:http://localhost:3000";
        return "redirect:http://ec2-13-209-105-111.ap-northeast-2.compute.amazonaws.com:3000";
    }

    @PostMapping("/authToken")
    public @ResponseBody
    HashMap<String, Object> authToken(@RequestBody String reqAccessToken) {       // 모델에 유저 정보
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        log.info(reqAccessToken);
        SessionUserDto sessionUserDto = (SessionUserDto) httpSession.getAttribute("user");

        log.info("roleKey:" + sessionUserDto.getRole().getKey());
        String sessionUserRole = sessionUserDto.getRole().getKey().toString();

        String accessToken = (String) httpSession.getAttribute("accessToken");
        Long userId = null;
        if (sessionUserRole.equals("ROLE_USER")) {
            resultMap.put("isMusician", false);
        } else {
            resultMap.put("isMusician", true);
        }
        if (sessionUserDto != null) {
            userId = userService.findUserIdByEmail(sessionUserDto.getEmail());
            resultMap.put("success", "1");
            resultMap.put("userId", String.valueOf(userId));
            resultMap.put("user", sessionUserDto);
            resultMap.put("accessToken", accessToken);
        } else {
            resultMap.put("success", "0");
        }
        log.info("role:"+resultMap.get("isMusician").toString());
        return resultMap;
    }

    @GetMapping("/main")
    public Map<String, Object> main() {
        return musicianService.getMainResponse();
    }
}
