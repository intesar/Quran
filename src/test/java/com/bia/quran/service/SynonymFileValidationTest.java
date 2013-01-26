
package com.bia.quran.service;

import com.bia.quran.entity.ResultDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "/spring/*.xml" })
@Transactional
public class SynonymFileValidationTest {
    protected static final Logger logger = LoggerFactory.getLogger(SynonymFileValidationTest.class);
    protected static final String SYNONYMS_FILE = "/data/ayah-synonyms.properties";
    @Autowired
    protected QuranServiceImpl quranServiceImpl;
    
    public SynonymFileValidationTest() {
    }

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }
    
    
    /**
     * Test of readData method, of class QuranServiceImpl.
     */
    @Test
    public void testSynonymFile() {
        logger.info("readData " + SYNONYMS_FILE);
        List<List<String>> data = readData();
        int totalSyn = 0;
        for ( List<String> list : data) {
            int prevHits = -1;
            String prevToken = null;
            if ( list == null || list.isEmpty() || list.size() == 1) {
                continue;
            }
            for (String term : list) {
                // ignore term
                if ( term == null || term.trim().isEmpty() ) {
                    continue;
                }
                // search
                ResultDto dto = quranServiceImpl.search(term);
                totalSyn++;
                
                int hits = dto.getAyahHits();
                // if first term 
                if ( prevToken == null) {
                    prevToken = term;
                }
                // if not first term
                if ( prevHits != -1 ) {
                    logger.info(prevToken + ", " + term);
                    Assert.assertEquals(prevHits, hits);
                }
                // assign to prev
                prevHits = hits;
                prevToken = term;
            }
        }
        logger.info("total synonyms processed ={} ", totalSyn);
    }
    
     public List<List<String>> readData() {
        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            logger.info("reading data from " + SYNONYMS_FILE);
            InputStream input = QuranServiceImpl.class.getResourceAsStream(SYNONYMS_FILE);
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
                    if ( line == null || line.startsWith("#") || line.trim().isEmpty())
                        continue;
                    String[] tokens = line.split(",");
                    data.add(Arrays.asList(tokens));
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
