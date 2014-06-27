package org.cny.jtf;

import com.bm.creators.SessionBeanFactory;
import com.bm.jndi.Ejb3UnitJndiBinder;
import org.apache.struts2.StrutsJUnit4TestCase;
import org.cny.jtf.z.ZFacade;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.io.File;
import java.io.InputStream;

public class Stf<T> extends StrutsJUnit4TestCase<T> {
    protected Etf2 etf;

    public Stf(Class<?>[] usedEntityBeans) {
        super();
        this.etf = new Etf2(usedEntityBeans);
    }

    public Class<ZFacade> getBeanClass() {
        return this.etf.getBeanClass();
    }

    public ZFacade getBeanToTest() {
        return this.etf.getBeanToTest();
    }

    public DataSource getDataSource() {
        return this.etf.getDataSource();
    }

    public EntityManager getEntityManager() {
        return this.etf.getEntityManager();
    }

    public Ejb3UnitJndiBinder getJndiBinder() {
        return this.etf.getJndiBinder();
    }

    public SessionBeanFactory<ZFacade> getSbFactory() {
        return this.getSbFactory();
    }

    @org.junit.Before
    public void setUp() throws Exception {
        super.setUp();
        this.etf.setUp();
    }

    public void setValueForField(Object forObject, String fieldName, Object toSet) {
        this.etf.setValueForField(forObject, fieldName, toSet);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        super.tearDown();
        this.etf.tearDown();
    }

    protected void initServletMockObjects() {
        resourceLoader = new DefaultResourceLoader();
        super.initServletMockObjects();
    }

    /**
     * the method to import the test data through the XLS file.
     *
     * @param path the XLS file path.
     * @throws Exception throwing the exception when import data error.
     */
    public void importData(String path) throws Exception {
        this.importData(path);
    }

    /**
     * the method to import the test data through the XLS file.
     *
     * @param path   the XLS file path.
     * @param schema the schema name.
     * @throws Exception throwing the exception when import data error.
     */
    public void importData(String path, String schema) throws Exception {
        this.etf.importData(path, schema);
    }

    /**
     * the method to import the test data through the XLS file.
     *
     * @param file the XLS file.
     * @throws Exception throwing the exception when import data error.
     */
    public void importData(File file) throws Exception {
        this.etf.importData(file);
    }

    /**
     * the method to import the test data through the XLS file.
     *
     * @param file   the XLS file.
     * @param schema the schema name.
     * @throws Exception throwing the exception when import data error.
     */
    public void importData(File file, String schema) throws Exception {
        this.etf.importData(file, schema);
    }

    /**
     * the method to import the test data through the XLS file.
     *
     * @param input the XLS input stream.
     * @throws Exception throwing the exception when import data error.
     */
    public void importData(InputStream input) throws Exception {
        this.etf.importData(input);
    }

    /**
     * the method to import the test data through the XLS file.
     *
     * @param input  the XLS input stream.
     * @param schema the schema name.
     * @throws Exception throwing the exception when import data error.
     */
    public void importData(InputStream input, String schema) throws Exception {
        this.etf.importData(input, schema);
    }
}
