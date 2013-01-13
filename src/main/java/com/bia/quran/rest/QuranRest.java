package com.bia.quran.rest;

import com.bia.quran.entity.Quran;
import com.bia.quran.service.QuranServiceImpl;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
@Path("/quranservice")
@Component
public class QuranRest {
    protected static final String SUCCESS_MSG = "Reinitialization of database is successful!";
    
    @Autowired
    protected QuranServiceImpl quranServiceImpl;

    /**
     * 
     * Searches entire db for the term
     * Search by sura number (e.g suraID:1, suraID:34. valid suraID values 1-114)
     * Search by Sura name   
     * Search by term
     * @param term
     *              
     * @return 
     */
    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Quran> search(String term) {
        return null;
    }
    
    /**
     * Reinitializes database
     * @return List<Quran>
     */
    @GET
    @Path("/reinitdb")
    @Produces({MediaType.APPLICATION_JSON})
    public String reinitDB() {
        this.quranServiceImpl.reinitDB();
        return SUCCESS_MSG;
    }
}
