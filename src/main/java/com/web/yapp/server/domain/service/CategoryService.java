package com.web.yapp.server.domain.service;

import com.web.yapp.server.controller.dto.MusicianDto;
import com.web.yapp.server.domain.*;
import com.web.yapp.server.domain.repository.AtmosphereRepository;
import com.web.yapp.server.domain.repository.GenreRepository;
import com.web.yapp.server.domain.repository.InstrumentRepository;
import com.web.yapp.server.domain.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final AtmosphereRepository atmosphereRepository;
    private final GenreRepository genreRepository;
    private final InstrumentRepository instrumentRepository;
    private final ThemeRepository themeRepository;

    public List<Atmosphere> findByAtmosphere(){
        return atmosphereRepository.findByAllAtmosphere();
    }

    public List<Genre> findByGenre(){
        return genreRepository.findByAllGenre();
    }

    public List<Instrument> findByInstrument()
    {
        return instrumentRepository.findByAllInstrument();
    }

    public List<Theme> findByTheme(){
        return themeRepository.findByAllTheme();
    }



}
