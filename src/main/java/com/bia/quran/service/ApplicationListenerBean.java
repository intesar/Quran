
package com.bia.quran.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
@Component
public class ApplicationListenerBean implements ApplicationListener<ContextRefreshedEvent> {

    protected static final Logger logger = Logger.getLogger(ApplicationListenerBean.class);
    
    @Autowired
    protected QuranServiceImpl quranServiceImpl;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //ApplicationContext applicationContext = event.getApplicationContext();
        // disable below in dev
        // On application start this code will create Quran table in the db and reinitializes it
        logger.info("ContextRefreshedEvent fired!");
        quranServiceImpl.reinitDB();
    }
}
