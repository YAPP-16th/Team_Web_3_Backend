package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Atmosphere;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AtmosphereRepository {
    public List<Atmosphere> findByAllAtmosphere() {
        return null;
    }
}
