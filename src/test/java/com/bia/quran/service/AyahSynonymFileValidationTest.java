package com.bia.quran.service;

import com.bia.quran.dao.AyahRepository;
import com.bia.quran.entity.Ayah;
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
    "/spring/*.xml"})
@Transactional
public class AyahSynonymFileValidationTest {

    protected static final Logger logger = LoggerFactory.getLogger(AyahSynonymFileValidationTest.class);
    @Autowired
    protected AyahRepository ayahRepository;
    @Autowired
    protected OOBSynonymReader synonymReader;

    public AyahSynonymFileValidationTest() {
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

        List<List<String>> data = synonymReader.readAyahData();
        int totalSyn = 0;
        for (List<String> list : data) {
            int prevHits = -1;
            String prevToken = null;
            if (list == null || list.isEmpty() || list.size() == 1) {
                continue;
            }
            for (String term : list) {
                // ignore term
                if (term == null || term.trim().isEmpty()) {
                    continue;
                }
                // search
                List<Ayah> ayahs = ayahRepository.search(term);
                totalSyn++;

                int hits = ayahs.size();
                // if first term 
                if (prevToken == null) {
                    prevToken = term;
                }
                // if not first term
                if (prevHits != -1) {
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
}
