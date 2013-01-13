package com.bia.quran.rest;

import com.bia.quran.entity.ResultDto;
import com.bia.quran.service.QuranServiceImpl;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
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
     * 
     * @param term
     *              
     * @return 
     */
    @GET
    @Path("/search/{term}")
    @Produces({MediaType.APPLICATION_JSON})
    public ResultDto search(@PathParam("term") String term) {
        return this.quranServiceImpl.search(term);
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
