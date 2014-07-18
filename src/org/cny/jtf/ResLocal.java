package org.cny.jtf;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

/**
 * Created by cny on 7/17/14.
 */
@Local
public interface ResLocal {
    public EntityManager getEm();

    public DataSource getDs();

    public UserTransaction getTx();
}
