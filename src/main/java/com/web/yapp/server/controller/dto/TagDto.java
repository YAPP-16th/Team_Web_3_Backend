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
    private String categoryNM;

    public Tag toEntity(){
        return Tag.builder()
                .tagNM(tagNM)
                .categoryNM(categoryNM)
                .build();
    }

    public TagDto(Tag Entity){
        this.tagNM = Entity.getTagNM();
        this.categoryNM = Entity.getCategoryNM();
    }
}
