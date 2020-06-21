package com.web.yapp.server.controller;

import com.web.yapp.server.domain.service.BookmarkService;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping("/{musicianId}")
    public void createBookmark(@PathVariable Long musicianId){
        bookmarkService.createBookmark(musicianId);
    }

//    @DeleteMapping("/{musicianId}")
//    public void deleteBookmark(@PathVariable Long musicianId){
//        bookmarkService.deleteBookmark(musicianId);
//    }


}
