/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.quran.daobase;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
public interface MyRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID> {
}
