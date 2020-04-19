package com.web.yapp.server.domain.repository;
import com.web.yapp.server.domain.Instrument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InstrumentRepository {
    public static List<Instrument> findByAllInstrument() {
        return null;
    }
}
