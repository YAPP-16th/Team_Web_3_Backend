package com.web.yapp.server.controller;

import com.web.yapp.server.domain.repository.ThemeRepository;
import com.web.yapp.server.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin("*")
@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 메인 카테고리 선택(분위기:1, 테마: 2, 악기: 3, 장르:4
     * @param categoryId
     * @return
     */
    @GetMapping("/category/{categoryId}")
    public String selectCategory(@PathVariable("categoryId") Long categoryId){


        if (categoryId == 1L) {
            categoryService.findByAtmosphere();
        }else if(categoryId == 2L){
            categoryService.findByTheme();
        }else if(categoryId == 3L){
            categoryService.findByInstrument();
        }else if(categoryId == 4L){
            categoryService.findByGenre();

        }

        return "categorySelectPage";

    }

}
