package com.bia.quran.dao;

import com.bia.quran.entity.Surah;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public interface SurahRespositorySearch {

    Surah searchBySurahName(String name);
}
