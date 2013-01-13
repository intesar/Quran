package com.bia.quran.dao;

import com.bia.quran.entity.Quran;
import java.util.List;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public interface QuranRespositorySearch {
    
    public List<Quran> search(String term);
}
