package com.bia.quran.dao;

import com.bia.quran.entity.Ayah;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public class AyahRepositoryImpl implements AyahRespositorySearch {

    protected static final Logger logger = LoggerFactory.getLogger(AyahRepositoryImpl.class);
    @PersistenceUnit(unitName = "quran-pu")
    private EntityManagerFactory emf;

    @Override
    public List<Ayah> search(String term) {

        logger.info("search = {}", term);
        EntityManager em = emf.createEntityManager();

        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);


        final QueryBuilder b = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Ayah.class).get();

        org.apache.lucene.search.Query luceneQuery =
                b.keyword()
                .onField(AyahConstants.AYAH_TEXT).boostedTo(3)
                .matching(term)
                .createQuery();

        FullTextQuery fullTextQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery);

        List<Ayah> result = fullTextQuery.getResultList();

        return result;
    }

    @Override
    public List<Ayah> searchBySurahName(String term) {

        logger.info("searchBySurahName = {}", term);
        EntityManager em = emf.createEntityManager();

        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);


        final QueryBuilder b = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Ayah.class).get();

        org.apache.lucene.search.Query luceneQuery =
                b.keyword()
                .onField(AyahConstants.SURAH_NAME).boostedTo(3)
                .matching(term)
                .createQuery();

        FullTextQuery fullTextQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery);

        List<Ayah> result = fullTextQuery.getResultList();

        return result;
    }

//    @Override
//    public List<Ayah> findBySurahId(Integer suraId) {
//        logger.info("findBySurahId = {}", suraId);
//        EntityManager em = emf.createEntityManager();
//
//        FullTextEntityManager fullTextEntityManager =
//                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
//
//
//        final QueryBuilder b = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder().forEntity(Ayah.class).get();
//
//        org.apache.lucene.search.Query luceneQuery =
//                b.keyword()
//                .onField(AyahConstants.SURAH_ID).boostedTo(3)
//                .matching(suraId)
//                .createQuery();
//
//        FullTextQuery fullTextQuery =
//                fullTextEntityManager.createFullTextQuery(luceneQuery);
//
//        Sort sort = new Sort(
//                new SortField(AyahConstants.AYAH_ID, SortField.INT));
//        fullTextQuery.setSort(sort);
//
//        List<Ayah> result = fullTextQuery.getResultList();
//
//        return result;
//    }
//
//    @Override
//    public List<Ayah> findBySurahIdBetween(Integer from, Integer to) {
//        logger.info("findBySurahIdBetween = {} - {}", from, to);
//        EntityManager em = emf.createEntityManager();
//
//        FullTextEntityManager fullTextEntityManager =
//                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
//
//
//        final QueryBuilder b = fullTextEntityManager.getSearchFactory()
//                .buildQueryBuilder().forEntity(Ayah.class).get();
//
//        org.apache.lucene.search.Query luceneQuery =
//                b.range()
//                .onField(AyahConstants.SURAH_ID)
//                .from(from).to(to)
//                .createQuery();
//
//        FullTextQuery fullTextQuery =
//                fullTextEntityManager.createFullTextQuery(luceneQuery);
//
//        org.apache.lucene.search.Sort sort = new org.apache.lucene.search.Sort(
//                new SortField(AyahConstants.AYAH_ID, SortField.INT));
//        fullTextQuery.setSort(sort);
//
//        List<Ayah> result = fullTextQuery.getResultList();
//
//        return result;
//    }
}
