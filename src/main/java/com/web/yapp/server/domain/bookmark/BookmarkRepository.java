package com.web.yapp.server.domain.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
