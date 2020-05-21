package com.web.yapp.server.controller;

import com.web.yapp.server.domain.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RequiredArgsConstructor
@Controller
public class FileController {
    private final S3Uploader s3Uploader;

    @GetMapping("/file") // 파일 업로드 테스트용 뷰
    public String uploadView(){
        return "upload";
    }

    @PostMapping("/upload") // 업로드 API. 파일 URL 리턴
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        String result = s3Uploader.upload(multipartFile, "static");
        System.out.println("결과 URL "+result); //결과 URL console에 출력
        return result;
    }
}
