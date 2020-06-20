package com.web.yapp.server.controller;

import com.web.yapp.server.domain.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @PostMapping("/bookmark")
    public void createBookmark(@RequestParam Long userId, @RequestParam Long musicianId){
        bookmarkService.createBookmark(userId, musicianId);
    }
    @GetMapping
    public void deleteBookmark(){

    }


}
