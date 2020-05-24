package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Atmosphere;
import com.web.yapp.server.domain.Instrument;
import com.web.yapp.server.domain.Theme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ThemeRepository {
    private final EntityManager em;
    /**
     * 테마 값 저장
     * @param theme
     */
    public void save(Theme theme){
        EntityManager em = this.em;
        em.persist(theme);
    }
    public List<Theme> findByAllTheme() {
        List<Theme> themeAllInfo = em.createQuery("select m from Theme m" , Theme.class)
                .getResultList();
        return themeAllInfo;
    }
}
