package com.bia.quran.dao;

import com.bia.quran.entity.Ayah;
import java.util.List;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public interface AyahRespositorySearch {

    List<Ayah> search(String term);

    List<Ayah> findBySurahId(Integer suraId);

    List<Ayah> findBySurahIdBetween(Integer from, Integer to);

    List<Ayah> searchBySurahName(String term);
}
