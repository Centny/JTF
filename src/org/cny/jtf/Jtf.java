package org.cny.jtf;

import org.cny.jtf.z.action.ZAction;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;

/**
 * Created by cny on 7/15/14.
 */
public class Jtf extends StrutsJTC<ZAction> {

    private static Logger log = LoggerFactory.getLogger(Jtf.class);

    protected EntityManager em;
    protected DataSource ds;
    protected UserTransaction tx;
    protected ResLocal res;
    //

    public Jtf() {
    }

    @Before
    public void initCtx() {
        TCtx.initCtx(new ResVisiblable() {
            @Override
            public void setRes(ResLocal res) {
                Jtf.this.res = res;
                Jtf.this.em = res.getEm();
                Jtf.this.ds = res.getDs();
                Jtf.this.tx = res.getTx();
            }
        });
    }

    public void bind(Object t) {
        try {
            new InitialContext().bind("inject", t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * the method to import the test data through the XLS file.
     *
     * @param path the XLS file path.
     * @throws Exception throwing the exception when import data error.
     */

    public void importData(String path) throws Exception {
        this.importData(new File(path));
    }

    /**
     * the method to import the test data through the XLS file.
     *
     * @param path   the XLS file path.
     * @param schema the schema name.
     * @throws Exception throwing the exception when import data error.
     */
    public void importData(String path, String schema) throws Exception {
        this.importData(new File(path), schema);
    }

    /**
     * the method to import the test data through the XLS file.
     *
     * @param file the XLS file.
     * @throws Exception throwing the exception when import data error.
     */
    public void importData(File file) throws Exception {
        this.importData(file, "");
    }

    /**
     * the method to import the test data through the XLS file.
     *
     * @param file   the XLS file.
     * @param schema the schema name.
     * @throws Exception throwing the exception when import data error.
     */
    public void importData(File file, String schema) throws Exception {
        this.importData(new FileInputStream(file), schema);
    }

    /**
     * the method to import the test data through the XLS file.
     *
     * @param input the XLS input stream.
     * @throws Exception throwing the exception when import data error.
     */
    public void importData(InputStream input) throws Exception {
        this.importData(input, "");
    }

    /**
     * the method to import the test data through the XLS file.
     *
     * @param input  the XLS input stream.
     * @param schema the schema name.
     * @throws Exception throwing the exception when import data error.
     */
    public void importData(InputStream input, String schema) throws Exception {
        if (input == null) {
            throw new Exception("input is null");
        }
//        TCtx.initCtx(this);
        if (schema == null || schema.trim().isEmpty()) {
            schema = TCtx.schema();
        }
        log.info("import data by schema:" + schema);
        // create the connection.
        Connection connection = this.ds.getConnection();
        IDatabaseConnection con = new DatabaseConnection(connection, schema, false);
        con.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new DefaultDataTypeFactory());
        // the data source.
        XlsDataSet ds = new XlsDataSet(input);
        ITable tables = ds.getTable(Tables.TablesMetaData.TABLES_NAME), tmp;
        int count = tables.getRowCount();
        String value;
        IDataSet ids;
        log.info("begin import the data for the " + count + " tables");
        for (int i = 0; i < count; i++) {
            // get the row value.
            value = tables.getValue(i, Tables.TablesMetaData.COLUMN_NAME) + "";
            // the information.
            log.info("importing the data for the table:" + value);
            // get the table.
            tmp = ds.getTable(value);
            // create one table data set through the XlsDataSet.
            ids = new DefaultDataSet(tmp);
            // clear the old data.
            this.clear(con, ids);
            // execute insert the data to database.
            DatabaseOperation.INSERT.execute(con, ids);
            // the imported information.
            log.info("imported " + tmp.getRowCount() + " row data to the table:" + value);
        }
        log.info("import data end");
        con.close();
        input.close();
    }

    /**
     * @param con the connection.
     * @param ds  the data set.
     */
    private void clear(IDatabaseConnection con, IDataSet ds) {
        try {
            DatabaseOperation.DELETE_ALL.execute(con, ds);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private boolean tx_opened = false;

    public void begin() {
        try {
            this.tx.begin();
            this.tx_opened = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void commit() {
        try {
            if (this.tx_opened) {
                this.tx.commit();
            }
            this.tx_opened = false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DataSource getDataSource() {
        return this.ds;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }

    public UserTransaction getTransaction() {
        return this.tx;
    }
}
