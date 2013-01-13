package com.bia.quran.service;

import com.bia.quran.dao.QuranRepository;
import com.bia.quran.entity.Quran;
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
 * @author
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
@Component
public class QuranServiceImpl {

    protected static final Logger logger = Logger.getLogger(QuranServiceImpl.class);
    protected static final String RAW_DATA_FILE = "/data/English-Yusuf-Ali-59 (2).csv";
    
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

    public List<Quran> getSuraById(Integer suraId) {
        return this.quranRepository.findBySuraId(suraId);
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
                int count = 1;
                List<Quran> list = new ArrayList<>();
                while (((line = x.readLine()) != null)) {
                    String[] tokens = line.split("\\|");
                    Quran quran = new Quran();
                    //quran.setDatabaseID(Short.parseShort(tokens[0]));
                    quran.setSuraId(Integer.parseInt(tokens[0]));
                    quran.setVerseId(Integer.parseInt(tokens[1]));
                    quran.setAyahText(tokens[2]);
                    list.add(quran);
                    count++;
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
