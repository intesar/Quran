package com.bia.quran.dao;

import com.bia.quran.entity.Surah;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public class SurahRepositoryImpl implements SurahRespositorySearch {

    protected static final Logger logger = LoggerFactory.getLogger(SurahRepositoryImpl.class);
    @PersistenceUnit(unitName = "quran-pu")
    private EntityManagerFactory emf;

    @Override
    public List<Surah> searchBySurahName(String term) {
        logger.info("searchBySurahName = {}", term);
        EntityManager em = emf.createEntityManager();

        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);


        final QueryBuilder b = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Surah.class).get();

        org.apache.lucene.search.Query luceneQuery =
                b.keyword()
                .onField(SurahConstants.NAME).boostedTo(3)
                .matching(term)
                .createQuery();

        FullTextQuery fullTextQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery);

        List<Surah> list = fullTextQuery.getResultList();
        
        logger.info("searchBySurahName result = {}", list);
        
        return list;

    }
}
