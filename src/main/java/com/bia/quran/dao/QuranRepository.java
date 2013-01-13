package com.bia.quran.dao;

import com.bia.quran.entity.Quran;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public interface QuranRepository extends PagingAndSortingRepository<Quran, Integer>, QuranRespositorySearch {

  public List<Quran> findByAyahText(@Param("ayahText") String ayahText);

  public List<Quran> findBySuraId(@Param("suraId") Integer suraId);
  
  public List<Quran> findBySuraIdBetween(Integer from, Integer to);

}
