package com.bia.quran.service;

import com.bia.quran.dao.QuranRepository;
import com.bia.quran.entity.Quran;
import com.bia.quran.entity.ResultDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Reads Quran data from csv
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
@Component
public class QuranServiceImpl {

    protected static final Logger logger = Logger.getLogger(QuranServiceImpl.class);
    protected static final String RAW_DATA_FILE = "/data/English-Yusuf-Ali-59 (2).csv";
    protected static final String SURA_NO_REGEX = "([1-9]|[1-9][0-9]|10[0-9]|11[0-4])";
    protected static final String SURA_PREFIX_NO_REGEX = "suraId:" + SURA_NO_REGEX;
    protected static final String SURA_NO_REGEX_BETWEEN = SURA_NO_REGEX + "-" + SURA_NO_REGEX;
    protected static final String SURA_PREFIX_NO_REGEX_BETWEEN = SURA_PREFIX_NO_REGEX + "-" + SURA_PREFIX_NO_REGEX;
    
    @Autowired
    protected QuranRepository quranRepository;

    /**
     * reinitializes db
     */
    public void reinitDB() {
        List<Quran> list = readData();
        quranRepository.deleteAll();
        logger.info("saving reinit data to db");
        quranRepository.save(list);
    }
    
    /*
     * Searches entire db for the term
     * Search by sura number (e.g 1, 23, 114, suraID:1, suraID:34. valid suraID values 1-114)
     * Search by sura number range (e.g 1-4, 100-114)
     * Search by Sura name   
     * Search by term
     */
    public ResultDto search(String term) {
        logger.info("Search term: " + term);
        List<Quran> list;
        ResultDto dto = new ResultDto();
        if (term.matches(SURA_NO_REGEX) || term.matches(SURA_PREFIX_NO_REGEX)) {
            String suraNo = term;
            String[] tokens = term.split(":");
            if (tokens.length > 1 ) {
                suraNo = tokens[1];
            }
            Integer suraId = Integer.parseInt(suraNo);
            logger.info("suraId : " + suraId);
            list = quranRepository.findBySuraId(suraId);
        } else if ( term.matches(SURA_NO_REGEX_BETWEEN)) {
            String[] fromTo = term.split("-");
            list = quranRepository.findBySuraIdBetween(Integer.parseInt(fromTo[0]), Integer.parseInt(fromTo[1]));
        } else {
            list = quranRepository.search(term);
        }
        dto.setAyahs(list);
        dto.setAyahHits(list == null ? 0 : list.size());
        return dto;
    }
    
    /**
     * Reads data from csv
     *
     * @return List<Quran>
     */
    public List<Quran> readData() {
        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            logger.info("reading data from " + RAW_DATA_FILE);
            InputStream input = QuranServiceImpl.class.getResourceAsStream(RAW_DATA_FILE);
            BufferedReader x = new BufferedReader(new InputStreamReader(input));
            try {
                String line; //not declared within while loop
                /*
                 * readLine is a bit quirky :
                 * it returns the content of a line MINUS the newline.
                 * it returns null only for the END of the stream.
                 * it returns an empty String if two newlines appear in a row.
                 */
                int ayahId = 1;
                List<Quran> list = new ArrayList<Quran>();
                while (((line = x.readLine()) != null)) {
                    String[] tokens = line.split("\\|");
                    Quran quran = new Quran();
                    quran.setAyahId(ayahId++);
                    quran.setSuraId(Integer.parseInt(tokens[0]));
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
}
