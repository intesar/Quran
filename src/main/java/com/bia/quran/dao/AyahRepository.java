package com.bia.quran.dao;

import com.bia.quran.entity.Ayah;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public interface AyahRepository extends PagingAndSortingRepository<Ayah, Integer>, AyahRespositorySearch {

    List<Ayah> findByAyahText(@Param("ayahText") String ayahText);
}
