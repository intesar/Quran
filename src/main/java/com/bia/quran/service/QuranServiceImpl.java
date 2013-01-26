package com.bia.quran.service;

import com.bia.quran.dao.AyahRepository;
import com.bia.quran.dao.SurahRepository;
import com.bia.quran.entity.Ayah;
import com.bia.quran.entity.ResultDto;
import com.bia.quran.entity.Surah;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Reads Ayah data from csv
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
@Component
public class QuranServiceImpl {

    protected static final Logger logger = LoggerFactory.getLogger(QuranServiceImpl.class);
    protected static final String RAW_DATA_FILE = "/data/English-Yusuf-Ali-59 (2).csv";
    protected static final String SURA_FILE = "/data/Sura-Numbers.csv";
    protected static final String SURA_NO_REGEX = "([1-9]|[1-9][0-9]|10[0-9]|11[0-4])";
    protected static final String SURA_PREFIX_NO_REGEX = "suraId:" + SURA_NO_REGEX;
    protected static final String SURA_NO_REGEX_BETWEEN = SURA_NO_REGEX + "-" + SURA_NO_REGEX;
    protected static final String SURA_PREFIX_NO_REGEX_BETWEEN = SURA_PREFIX_NO_REGEX + "-" + SURA_PREFIX_NO_REGEX;
    protected static final String PIPE = "\\|";
    @Autowired
    protected AyahRepository ayahRepository;
    @Autowired
    protected SurahRepository surahRepository;

    /*
     * Searches entire db for the term Search by sura number (e.g 1, 23, 114,
     * suraID:1, suraID:34. valid suraID values 1-114) Search by sura number
     * range (e.g 1-4, 100-114) Search by Sura name Search by term
     */
    public ResultDto search(String term) {
        logger.info("Search term: " + term);
        List<Ayah> list;
        ResultDto dto = new ResultDto();
        if (term.matches(SURA_NO_REGEX) || term.matches(SURA_PREFIX_NO_REGEX)) {
            String suraNo = term;
            String[] tokens = term.split(":");
            if (tokens.length > 1) {
                suraNo = tokens[1];
            }
            Integer suraId = Integer.parseInt(suraNo);
            logger.info("suraId : " + suraId);
            list = ayahRepository.findBySurahId(suraId);
        } else if (term.matches(SURA_NO_REGEX_BETWEEN)) {
            String[] fromTo = term.split("-");
            list = ayahRepository.findBySurahIdBetween(Integer.parseInt(fromTo[0]), Integer.parseInt(fromTo[1]));
        } else {
            list = ayahRepository.search(term);
        }
        dto.setAyahs(list);
        dto.setAyahHits(list == null ? 0 : list.size());
        return dto;
    }

    /**
     * reinitializes db
     */
    public void reinitDB() {
        logger.info("reniting quran data...");

        logger.info(("loading Surah's"));
        surahRepository.deleteAll();
        List<Surah> surahs = readSuraData();
        surahRepository.save(surahs);

        logger.info("loading Ayah's");
        ayahRepository.deleteAll();
        List<Ayah> list = readAyahData();
        ayahRepository.save(list);
    }

    /**
     * Reads data from csv
     *
     * @return List<Quran>
     */
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
                while (((line = x.readLine()) != null)) {
                    String[] tokens = line.split(PIPE);
                    Ayah quran = new Ayah();
                    quran.setAyahId(ayahId++);

                    Surah surah = surahRepository.findOne(Integer.parseInt(tokens[0]));
                    quran.setSurah(surah);

                    quran.setVerseId(Integer.parseInt(tokens[1]));
                    quran.setAyahText(tokens[2]);
                    list.add(quran);
                }
                return list;
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Surah> readSuraData() {
        try {
            logger.info("reading sura from " + SURA_FILE);
            InputStream input = QuranServiceImpl.class.getResourceAsStream(SURA_FILE);
            BufferedReader x = new BufferedReader(new InputStreamReader(input));
            try {
                String line;
                int suraId = 1;
                List<Surah> list = new LinkedList<Surah>();
                while(((line = x.readLine()) != null)){
                    String[] tokens = line.split(PIPE);
                    Surah surah = new Surah();
                    surah.setId(Integer.parseInt(tokens[0]));
                    surah.setName(tokens[1]);
                    list.add(surah);
                }
                return list;
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }
    //TODO
//    xRename Quran to Ayah
//    xQuranRepository
//    xQuranRepositoryImpl
//    xQuranREpositorySear
//    xQuran
//    xQuranConstants
}
