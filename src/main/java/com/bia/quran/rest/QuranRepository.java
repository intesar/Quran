package com.bia.quran.rest;

import com.bia.quran.entity.Quran;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.repository.annotation.RestResource;

/**
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
@RestResource(path = "quran")
public interface QuranRepository extends PagingAndSortingRepository<Quran, Integer> {

  //@RestResource(path = "ayah", rel = "names")
  public List<Quran> findByAyahText(@Param("ayahText") String ayahText);

  //@RestResource(path = "sura", rel = "names")
  public List<Quran> findBySuraID(@Param("suraID") Integer suraID);

}
