package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TagDto {
    private String tagNM;
    private int category;

    public Tag toEntity(){
        return Tag.builder()
                .tagNM(tagNM)
                .category(category)
                .build();
    }

    public TagDto(Tag Entity){
        this.tagNM = Entity.getTagNM();
        this.category = Entity.getCategory();
    }
}
