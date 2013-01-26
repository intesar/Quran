package com.bia.quran.dao;

import com.bia.quran.entity.Ayah;
import java.util.List;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public interface AyahRespositorySearch {
    
    public List<Ayah> search(String term);
}
