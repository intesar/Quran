package com.bia.quran.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
@Component
public class OOBSynonymReader {

    protected static final Logger logger = LoggerFactory.getLogger(OOBSynonymReader.class);
    protected static final String AYAH_SYNONYMS_FILE = "/data/ayah-synonyms.properties";
    protected static final String SURAH_SYNONYMS_FILE = "/data/surah-synonyms.properties";

    public List<List<String>> readAyahData() {
        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            logger.info("reading data from " + AYAH_SYNONYMS_FILE);
            InputStream input = QuranServiceImpl.class.getResourceAsStream(AYAH_SYNONYMS_FILE);
            BufferedReader x = new BufferedReader(new InputStreamReader(input));
            List<List<String>> data = new LinkedList<List<String>>();
            try {
                String line; //not declared within while loop
                /*
                 * readLine is a bit quirky :
                 * it returns the content of a line MINUS the newline.
                 * it returns null only for the END of the stream.
                 * it returns an empty String if two newlines appear in a row.
                 */
                while (((line = x.readLine()) != null)) {
                    if (line == null || line.startsWith("#") || line.trim().isEmpty()) {
                        continue;
                    }
                    List<String> tokenList = new LinkedList<String>();
                    String[] tokens = line.split(",");
                    for ( String token : tokens) {
                        tokenList.add(token.trim());
                    }
                    data.add(tokenList);
                }
                return data;
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<List<String>> readSurahData() {
        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            logger.info("reading data from " + SURAH_SYNONYMS_FILE);
            InputStream input = QuranServiceImpl.class.getResourceAsStream(SURAH_SYNONYMS_FILE);
            BufferedReader x = new BufferedReader(new InputStreamReader(input));
            List<List<String>> data = new LinkedList<List<String>>();
            try {
                String line; //not declared within while loop
                /*
                 * readLine is a bit quirky :
                 * it returns the content of a line MINUS the newline.
                 * it returns null only for the END of the stream.
                 * it returns an empty String if two newlines appear in a row.
                 */
                while (((line = x.readLine()) != null)) {
                    if (line == null || line.startsWith("#") || line.trim().isEmpty()) {
                        continue;
                    }
                    List<String> tokenList = new LinkedList<String>();
                    String[] tokens = line.split(",");
                    for ( String token : tokens) {
                        tokenList.add(token.trim());
                    }
                    data.add(tokenList);
                }
                return data;
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
