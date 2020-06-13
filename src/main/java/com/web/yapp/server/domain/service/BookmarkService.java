package com.web.yapp.server.domain.service;

import com.web.yapp.server.domain.Bookmark;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.User;
import com.web.yapp.server.domain.repository.BookmarkRepository;
import com.web.yapp.server.domain.repository.MusicianRepository;
import com.web.yapp.server.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final MusicianRepository musicianRepository;

    @Transactional
    public void createBookmark(Long userId, Long musicianId) {
        User user = userRepository.findUserById(userId);
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
    public void deleteBookmark(Long userId, Long musicianId){

    }

    @Transactional
    public void upBookmarkCount(Musician musician){
        musicianRepository.upBookmarkCount(musician.getId());
    }

    @Transactional
    public void downBookmarkCount(Musician musician){
        musicianRepository.downBookmarkCount(musician.getId());
    }
}
