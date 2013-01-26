package com.bia.quran.dao;

import com.bia.quran.entity.Surah;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public interface SurahRepository extends PagingAndSortingRepository<Surah, Integer>, SurahRespositorySearch {

    @Query("SELECT s FROM Surah s ORDER by id")
    List<Surah> loadAll();
}
