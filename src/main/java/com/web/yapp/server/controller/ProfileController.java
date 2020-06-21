package com.web.yapp.server.controller;

import com.web.yapp.server.controller.dto.BookmarkDto;
import com.web.yapp.server.controller.dto.ContractDto;
import com.web.yapp.server.domain.service.BookmarkService;
import com.web.yapp.server.domain.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final BookmarkService bookmarkService;
    private final ContractService contractService;
    /**
     * 유저 마이페이지 조회
     * @param userId
     * @return
     */
    @GetMapping("/userProfile")
    public HashMap<String,Object> userProfile (@RequestParam Long userId){
        HashMap<String,Object> resultMap = new HashMap<String,Object>();

        /**
         * 클라이언트 request: userId
         */

        // 북마크 정보 조회
        BookmarkDto bookmarkList = bookmarkService.findByIdBookmark(userId);
        ContractDto contractList = contractService.findByIdContract(userId);

        resultMap.put("bookmarkList", bookmarkList);
        resultMap.put("contractList", contractList);
        resultMap.put("success","1");
        return resultMap;
    }

    /**
     * 뮤지션 마이페이지 조회
     * @param musicianId
     * @return
     */
    @GetMapping("/musicianProfile")
    public HashMap<String,Object> musicianProfile (@RequestParam Long musicianId){
        HashMap<String,Object> resultMap = new HashMap<String,Object>();

        /**
         * 클라이언트 request: musicianId
         */
        bookmarkService.findByIdBookmark(musicianId);
        contractService.findByIdContract(musicianId);
        resultMap.put("success","1");
        return resultMap;
    }







}
