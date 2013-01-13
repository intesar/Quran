package com.bia.quran.dao;

import com.bia.quran.entity.Quran;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.apache.log4j.Logger;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public class QuranRepositoryImpl implements QuranRespositorySearch {

    protected static final Logger logger = Logger.getLogger(QuranRepositoryImpl.class);
    
    @PersistenceUnit(unitName="quran-pu")
    private EntityManagerFactory emf;
    
//    @PersistenceContext
//    private EntityManager entityManager;

    @Override
    public List<Quran> search(String term) {
        logger.info("Searching for : " + term);
        logger.info("entityManager is : " + emf);

        EntityManager em = emf.createEntityManager();
        
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);


        final QueryBuilder b = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Quran.class).get();

        org.apache.lucene.search.Query luceneQuery =
                b.keyword()
                .onField("ayahText").boostedTo(3)
                .matching(term)
                .createQuery();
        javax.persistence.Query fullTextQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery);

        List<Quran> result = fullTextQuery.getResultList();
        
        return result;
    }
}
