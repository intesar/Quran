/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.quran.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mdshannan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "/spring/*.xml" })
@Transactional
public class AyahRepositoryImplTest {
    @Autowired
    protected AyahRepository ayahRepository;
    public AyahRepositoryImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of queryTopTerms method, of class QuranRepositoryImpl.
     */
    @Test
    public void testQueryTopTerms() {
        System.out.println("queryTopTerms");
    }
}
