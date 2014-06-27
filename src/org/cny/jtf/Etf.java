package org.cny.jtf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bm.testsuite.junit4.BaseSessionBeanJUnit4Fixture;

/**
 * Perform a base test class to use the data which import through the XLS file.
 *
 * @param <T> the target session bean.
 * @author Centny.
 */
public class Etf<T> extends BaseSessionBeanJUnit4Fixture<T> {

    /**
     * the log.
     */
    final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * @param sessionBeanToTest the session bean.
     * @param usedEntityBeans   the target entities.
     */
    public Etf(Class<T> sessionBeanToTest,
               Class<?>[] usedEntityBeans) {
        super(sessionBeanToTest, usedEntityBeans);
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
        if (schema == null || schema.trim().isEmpty()) {
            schema = this.loadSchema();
        }
        if (schema == null) {
            schema = "";
        }
        this.log.info("import data by schema:" + schema);
        // create the connection.
        Connection connection = this.getDataSource().getConnection();
        IDatabaseConnection con = new DatabaseConnection(connection, schema, false);
        con.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new DefaultDataTypeFactory());
        // the data source.
        XlsDataSet ds = new XlsDataSet(input);
        ITable tables = ds.getTable(Tables.TablesMetaData.TABLES_NAME), tmp;
        int count = tables.getRowCount();
        String value;
        IDataSet ids;
        this.log.info("begin import the data for the " + count + " tables");
        for (int i = 0; i < count; i++) {
            // get the row value.
            value = tables.getValue(i, Tables.TablesMetaData.COLUMN_NAME) + "";
            // the information.
            this.log.info("importing the data for the table:" + value);
            // get the table.
            tmp = ds.getTable(value);
            // create one table data set through the XlsDataSet.
            ids = new DefaultDataSet(tmp);
            // clear the old data.
            this.clear(con, ids);
            // execute insert the data to database.
            DatabaseOperation.INSERT.execute(con, ids);
            // the imported information.
            this.log.info("imported " + tmp.getRowCount() + " row data to the table:" + value);
        }
        this.log.info("import data end");
        con.close();
        input.close();
    }

    /**
     * load jtf properties.
     *
     * @return Properties.
     * @throws IOException
     */
    private Properties loadEtf() throws IOException {
        Properties etf = new Properties();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("jtf.properties");
        if (in != null) {
            etf.load(in);
        }
        return etf;
    }

    /**
     * load schema by properties field.
     *
     * @return schema.
     * @throws IOException
     */
    private String loadSchema() throws IOException {
        return this.loadEtf().getProperty("jtf.schema");
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
}
