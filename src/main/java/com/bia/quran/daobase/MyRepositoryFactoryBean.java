/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.quran.daobase;

import com.bia.quran.daobase.MyRepository;
import com.bia.quran.daobase.MyRepositoryImpl;
import com.bia.quran.service.QuranServiceImpl;
import java.io.Serializable;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 *
 * @author mdshannan
 */
public class MyRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {
    
    protected static final Logger logger = Logger.getLogger(MyRepositoryFactoryBean.class);

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {

        return new MyRepositoryFactory(entityManager);
    }

    private static class MyRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

        private EntityManager entityManager;

        public MyRepositoryFactory(EntityManager entityManager) {
            super(entityManager);

            this.entityManager = entityManager;
        }

        @Override
        protected Object getTargetRepository(RepositoryMetadata metadata) {
            logger.info("getTargetRepository " + metadata);
            return new MyRepositoryImpl<>((Class<T>) metadata.getDomainType(), entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

            // The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
            //to check for QueryDslJpaRepository's which is out of scope.
            return MyRepository.class;
        }
    }
}
