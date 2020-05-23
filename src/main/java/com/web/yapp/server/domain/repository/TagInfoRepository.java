package com.web.yapp.server.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class TagInfoRepository {
    private final EntityManager em;


}
