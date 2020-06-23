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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserClassRepository userClassRepository;
    private final MusicianRepository musicianRepository;
    private final HttpSession httpSession;

    @Transactional
    public boolean createBookmark(Long musicianId) {
        SessionUserDto sessionUserDto;
        String email = "";
        try{
            sessionUserDto = (SessionUserDto) httpSession.getAttribute("user");
            email = sessionUserDto.getEmail();
        }catch (NullPointerException e){
            log.info("BookmarkService createBookmark 로그인이 필요합니다. error message : "+e.getMessage());
            return false;
        }

        User user = userClassRepository.findUserByEmail(email);
        Musician musician = musicianRepository.findOne(musicianId);
        Bookmark bookmark = Bookmark
                .builder()
                .user(user)
                .musician(musician)
                .build();
        bookmarkRepository.save(bookmark);
        log.info("insert 전  Cnt:" + musician.getBookmarkCount());

        upBookmarkCount(musician);
        log.info("inset 후  Cnt:" + musician.getBookmarkCount());

        if(bookmark.getId() != null) return true;
        else return false;
    }

    @Transactional
    public boolean deleteBookmark(Long musicianId){
        SessionUserDto sessionUserDto = (SessionUserDto) httpSession.getAttribute("user");
        String email = sessionUserDto.getEmail();
        User user = userClassRepository.findUserByEmail(email);
        Musician musician = musicianRepository.findOne(musicianId);
        int rowCnt = bookmarkRepository.delete(user.getId(),musicianId);
        log.info("delte 전  Cnt:" + musician.getBookmarkCount());

        downBookmarkCount(musician);
        log.info("delete 후 Cnt:" + musician.getBookmarkCount());
        if(rowCnt == 1) return true;
        else return false;

    }
    @Transactional
    public void upBookmarkCount(Musician musician){
        //MusicianDto = musician;
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
