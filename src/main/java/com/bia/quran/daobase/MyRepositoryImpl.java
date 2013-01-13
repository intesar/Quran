/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.quran.daobase;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author mdshannan
 */
@NoRepositoryBean
public class MyRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements MyRepository<T, ID> {

    protected EntityManager entityManager;

    
    // There are two constructors to choose from, either can be used.
    public MyRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);

        // This is the recommended method for accessing inherited class dependencies.
        this.entityManager = entityManager;
    }
}
