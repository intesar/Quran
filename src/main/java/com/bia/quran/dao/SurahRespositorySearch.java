package com.bia.quran.dao;

import com.bia.quran.entity.Surah;
import java.util.List;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public interface SurahRespositorySearch {

    List<Surah> searchBySurahName(String name);
}
