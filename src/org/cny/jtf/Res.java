package org.cny.jtf;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

/**
 * Created by cny on 7/16/14.
 */
@Stateless(name = "Res")
@TransactionManagement(TransactionManagementType.BEAN)
public class Res implements ResLocal {
    @PersistenceContext
    EntityManager em;
    @Resource
    DataSource ds;
    @Resource
    UserTransaction tx;

    @Override
    public EntityManager getEm() {
        return this.em;
    }

    @Override
    public DataSource getDs() {
        return this.ds;
    }

    @Override
    public UserTransaction getTx() {
        return this.tx;
    }
}
