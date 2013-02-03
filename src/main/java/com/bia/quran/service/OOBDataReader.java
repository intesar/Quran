package com.bia.quran.service;

import com.bia.quran.dao.SurahRepository;
import com.bia.quran.entity.Ayah;
import com.bia.quran.entity.Surah;
import com.bia.quran.entity.Video;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 * @author Atef A. Ahmed
 */
@Component
public class OOBDataReader {

    protected static final Logger logger = LoggerFactory.getLogger(OOBDataReader.class);
    protected static final String RAW_DATA_FILE = "/data/English-Yusuf-Ali-59 (2).csv";
    protected static final String SURA_FILE = "/data/Sura-Numbers.csv";
    protected static final String MISHARI_YOUTUBE = "/data/surah_mishari_youtube.csv";
    protected static final String PIPE = "\\|";
    @Autowired
    protected SurahRepository surahRepository;

    /**
     * Reads data from Surah file
     *
     * @return List<Surah>
     */
    public List<Surah> readSuraData() {
        try {
            logger.info("reading sura from " + SURA_FILE);
            InputStream input = QuranServiceImpl.class.getResourceAsStream(SURA_FILE);
            BufferedReader x = new BufferedReader(new InputStreamReader(input));
            try {
                String line;
                List<Surah> list = new LinkedList<Surah>();
                int linkUrl = 1;
                while (((line = x.readLine()) != null)) {
                    String[] tokens = line.split(PIPE);
                    Surah surah = new Surah();
                    surah.setId(Integer.parseInt(tokens[0]));
                    surah.setName(tokens[1]);                    

                    Map<Integer,String> link = readLinks();
                    String links = link.get(linkUrl).toString();                    

                    Video video = new Video("test", "test", links, "youtube");
                    surah.getVideos().add(video);
                    list.add(surah);
                    linkUrl++;
                }
                return list;
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Reads data from csv
     *
     * @return List<Quran>
     */

    public Map<Integer,String> readLinks() {
        try {            
            InputStream input = QuranServiceImpl.class.getResourceAsStream(MISHARI_YOUTUBE);            
            BufferedReader x = new BufferedReader(new InputStreamReader(input));
            
            try {                
                String line;
                Map<Integer, String> map = new HashMap<Integer, String>();

                while ((line = x.readLine()) != null) {
                    String[] tokens = line.split(PIPE);
                                        
                    Integer surahId = Integer.parseInt(tokens[0]);
                  
                    map.put(surahId, tokens[1]);
                }                
                return map;
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Ayah> readAyahData() {
        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            logger.info("reading data from " + RAW_DATA_FILE);
            InputStream input = QuranServiceImpl.class.getResourceAsStream(RAW_DATA_FILE);
            BufferedReader x = new BufferedReader(new InputStreamReader(input));
            try {
                String line; //not declared within while loop
                /*
                 * readLine is a bit quirky : it returns the content of a line
                 * MINUS the newline. it returns null only for the END of the
                 * stream. it returns an empty String if two newlines appear in
                 * a row.
                 */
                int ayahId = 1;
                List<Ayah> list = new ArrayList<Ayah>();
                List<Surah> surahs = surahRepository.loadAll();
                while (((line = x.readLine()) != null)) {
                    String[] tokens = line.split(PIPE);
                    Ayah ayah = new Ayah();

                    Integer surahId = Integer.parseInt(tokens[0]);
                    Surah surah = surahs.get(surahId - 1);
                    if (surah.getId() != surahId) {
                        throw new RuntimeException("Error loading Surah, invalid sort");
                    }
                    ayah.setSurah(surah);
                    ayah.setId(ayahId++);
                    ayah.setSurahVerseId(Integer.parseInt(tokens[1]));
                    ayah.setAyahText(tokens[2]);
                    list.add(ayah);
                }
                return list;
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
