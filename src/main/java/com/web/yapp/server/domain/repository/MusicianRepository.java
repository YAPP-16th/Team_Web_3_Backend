package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.MusicianTag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import springfox.documentation.service.Tags;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MusicianRepository{
    private final EntityManager em;
    


    /**
     * 뮤지션 값 저장
     * @param musician
     */
    public void save(Musician musician){
        EntityManager em = this.em;
        em.persist(musician);
    }

    /**
     * id값으로 뮤지션 조회
     * @param id
     * @return
     */
    public Musician findOne(Long id){
        Musician musician = em.find(Musician.class, id);
        return musician;
    }

    /**
     * 뮤지션 닉네임으로 값 조회
     * @param nickNm
     * @return
     */
    public List<Musician> findByNickNm(String nickNm){
        return em.createQuery("select m from Musician m where m.nickNm = :nickNm", Musician.class)
                .setParameter("nickNm", nickNm)
                .getResultList();
    }

    public Musician findByUserNm(String userNm){
        return em.createQuery("select m from Musician m where m.userId.name = :userNm", Musician.class)
                .setParameter("userNm", userNm)
                .getSingleResult();
    }


    /**
     * 모든값 조회
     * @return
     */
    public List<Musician> findAllMusician(){
        List<Musician> musicianAllInfo = em.createQuery("select m from Musician m" , Musician.class)
                .getResultList();
        return musicianAllInfo;
    }

    /**
     * 큐레이션 값 조회
     * @return
     */
    public List<Musician> findCurationMusician(){
        List<Musician> musicianCurationInfo = em.createQuery("select m from Musician m" , Musician.class)
                .getResultList();
        return musicianCurationInfo;
    }

    /**
     * 새로 등장한 뮤지션
     */
    public List<Musician> findMusicianByBookmark(){
        List<Musician> musicianChoiceInfo;
        try{
            musicianChoiceInfo = em.createQuery("select m from Musician m order by m.bookmarkCount desc"  , Musician.class)
                    .setFirstResult(0)
                    .setMaxResults(9)
                    .getResultList();
        }
        catch (NoResultException e){
            log.error("MusicianRepository findMusicianByBookmark :"+e.getMessage());
            musicianChoiceInfo = null;
        }
        return musicianChoiceInfo;
    }

    /**
     * 리스너들의 선택
     */
    public List<Musician> findMusicianByNew(){
        List<Musician> musicians;
        try{
            musicians = em.createQuery("select m from Musician m order by m.createdDate asc " , Musician.class)
                    .setFirstResult(0)
                    .setMaxResults(9)
                    .getResultList();

        }
        catch (NoResultException e){
            log.error("MusicianRepository findMusicianByNew :"+e.getMessage());
            musicians = null;
        }
        return musicians;
    }
    /**
     * 검색 페이지 - 메인
     */
    /*public List<Musician> findMusicianByAllSearch(String categoryNM){

        List<Musician> musicians;
        try{
           if(categoryNM != "전체") {
               musicians = em.createQuery("select m from Musician m order by m.createdDate asc " , Musician.class)
                       .setFirstResult(0)
                       .setMaxResults(9)
                       .getResultList();
            }
        }
        catch (NoResultException e){
            log.error("MusicianRepository findMusicianByNew :"+e.getMessage());
            musicians = null;
        }
        return musicians;
    }*/
    /**
     * 검색페이지 카테고리 메인
     */
   public List<Musician> findMusicianBySearch(String categoryNM){

        List<MusicianTag> musicianTags;
        List<Musician> musicians = null;
        List<Long> musicianIdList;

        try{
            musicianIdList = em.createQuery("select t.musician.id from MusicianTag t where t.categoryNM = :categoryNM")
                    .setParameter("categoryNM", categoryNM)
                    .getResultList();

            if(categoryNM.equals("선택안함")){
                musicians = em.createQuery("select m from Musician m order by m.createdDate asc ")
                        .getResultList();
            }
            else if(categoryNM.equals("분위기") || categoryNM.equals("테마") || categoryNM.equals("장르") || categoryNM.equals("악기")){
                 for(int i=0; i < musicianIdList.size(); i++){
                         Long musicianId = musicianIdList.get(i);
                         musicians = em.createQuery("select m from Musician m where m.id = :musicianId")
                                 .setParameter("musicianId", musicianId)
                                 .getResultList();

                 }
            }
        }
        catch (NoResultException e){
            log.error("MusicianRepository findMusicianByNew :"+e.getMessage());
            musicians = null;
        }
        return musicians;
    }

    public void upBookmarkCount(Long musicianId){ //잘못된 뮤지션 id가 들어왔을 때 처리하기
        em.createQuery("update Musician m set m.bookmarkCount = m.bookmarkCount+1 where m.id = :musicianId")
                .setParameter("musicianId",musicianId);
    }

    public void downBookmarkCount(Long musicianId){
        em.createQuery("update Musician m set m.bookmarkCount = m.bookmarkCount-1 where m.id = :musicianId")
                .setParameter("musicianId",musicianId);
    }

}