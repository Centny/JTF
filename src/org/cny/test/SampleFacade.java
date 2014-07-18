package org.cny.test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Centny.
 */
@Stateless(name = "Sample")
public class SampleFacade implements SampleLocal {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Object list() {
        return em.createQuery("select o from SAMPLE o").getResultList();
    }
}