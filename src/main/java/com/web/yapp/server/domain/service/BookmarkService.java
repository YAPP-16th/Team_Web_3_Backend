package com.web.yapp.server.domain.service;

import com.web.yapp.server.controller.dto.BookmarkDto;
import com.web.yapp.server.controller.dto.MusicianDto;
import com.web.yapp.server.controller.dto.SessionUserDto;
import com.web.yapp.server.domain.Bookmark;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.User;
import com.web.yapp.server.domain.repository.BookmarkRepository;
import com.web.yapp.server.domain.repository.MusicianRepository;
import com.web.yapp.server.domain.repository.UserClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserClassRepository userClassRepository;
    private final MusicianRepository musicianRepository;
    private final HttpSession httpSession;

    @Transactional
    public void createBookmark(Long musicianId) {
        SessionUserDto sessionUserDto = (SessionUserDto) httpSession.getAttribute("user");
        String email = sessionUserDto.getEmail();
        User user = userClassRepository.findUserByEmail(email);
        Musician musician = musicianRepository.findOne(musicianId);
        Bookmark bookmark = Bookmark
                .builder()
                .user(user)
                .musician(musician)
                .build();
        bookmarkRepository.save(bookmark);
        upBookmarkCount(musician);
    }

    @Transactional
    public void deleteBookmark(Long musicianId){
        SessionUserDto sessionUserDto = (SessionUserDto) httpSession.getAttribute("user");
        String email = sessionUserDto.getEmail();
        User user = userClassRepository.findUserByEmail(email);
        Musician musician = musicianRepository.findOne(musicianId);


    }
    @Transactional
    public void upBookmarkCount(Musician musician){
        musicianRepository.upBookmarkCount(musician.getId());
    }

    @Transactional
    public void downBookmarkCount(Musician musician){
        musicianRepository.downBookmarkCount(musician.getId());
    }


    /**
     * 유저 ID 조회
     * @param id
     * @return
     */

    public BookmarkDto findByIdBookmark(Long id){
        return new BookmarkDto(bookmarkRepository.findByUserId(id));
    }
}
