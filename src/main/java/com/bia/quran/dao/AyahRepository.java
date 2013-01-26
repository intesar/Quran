package com.bia.quran.dao;

import com.bia.quran.entity.Ayah;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public interface AyahRepository extends PagingAndSortingRepository<Ayah, Integer>, AyahRespositorySearch {

  public List<Ayah> findByAyahText(@Param("ayahText") String ayahText);

//  @Query("SELECT a FROM Ayah a WHERE a.sura.id = ?1")
  public List<Ayah> findBySurahId(Integer suraId);
  
//  @Query("SELECT a FROM Ayah a WHERE a.sura.id BETWEEN ?1 AND ?2")
  public List<Ayah> findBySurahIdBetween(Integer from, Integer to);

}
