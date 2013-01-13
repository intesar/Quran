
package com.bia.quran.service;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public class QuranServiceImplTest {
    
    public QuranServiceImplTest() {
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
    public void testSuraRegex() {
        System.out.println("readData " + QuranServiceImpl.SURA_NO_REGEX);
        assertTrue("1".matches(QuranServiceImpl.SURA_NO_REGEX));
        assertTrue("10".matches(QuranServiceImpl.SURA_NO_REGEX));
        assertTrue("20".matches(QuranServiceImpl.SURA_NO_REGEX));
        assertTrue("41".matches(QuranServiceImpl.SURA_NO_REGEX));
        assertTrue("100".matches(QuranServiceImpl.SURA_NO_REGEX));
        assertTrue("114".matches(QuranServiceImpl.SURA_NO_REGEX));
        
        assertFalse("0".matches(QuranServiceImpl.SURA_NO_REGEX));
        assertFalse("115".matches(QuranServiceImpl.SURA_NO_REGEX));
        
        
    }
}
