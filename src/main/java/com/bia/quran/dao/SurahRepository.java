package com.bia.quran.dao;

import com.bia.quran.entity.Surah;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public interface SurahRepository extends PagingAndSortingRepository<Surah, Integer> {

}
