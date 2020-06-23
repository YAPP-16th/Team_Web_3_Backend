package com.web.yapp.server.controller;

import com.web.yapp.server.domain.service.BookmarkService;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping("/{musicianId}")
    public Map<String, Object> createBookmark(@PathVariable Long musicianId){
        Map<String,Object> map = new HashMap<String, Object>();
        if(bookmarkService.createBookmark(musicianId)){
            map.put("success","true");
        }else map.put("success","false");
        return map;
    }

    @DeleteMapping("/{musicianId}")
    public Map<String,Object> deleteBookmark(@PathVariable Long musicianId){
        Map<String,Object> map = new HashMap<String, Object>();
        if(bookmarkService.deleteBookmark(musicianId)){
            map.put("success","true");
        }else map.put("success","false");
        return map;
    }


}
