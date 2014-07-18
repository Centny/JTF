package org.cny.jtf;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSetWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Centny
 */
public class ExportData {
    /**
     * the log.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(ExportData.class);

    /**
     * Perform a method to export the data from database to the XLS file.
     *
     * @param profile  the database profile.
     * @param os the XLS file path.
     */
    @SuppressWarnings("deprecation")
    public static void exportData(DatabaseProfile profile, OutputStream os)
            throws Exception {
        profile.print();
        // create the database profile.
        IDatabaseConnection con;
        String name = profile.getDriverClass();
        Class.forName(name);
        // create the database connection.
        Connection connection = DriverManager.getConnection(profile.getURL(),
                profile.getUser(), profile.getPassword());
        // create the oracle database connection.
        con = new DatabaseConnection(connection, profile.getSchema(), false);
        con.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new DefaultDataTypeFactory());
        IDataSet ds;
        // create the data set. it will include all table.
        ds = new TablesDataSet(profile.getTargets(), con);
        // the XLS file writer.it can export the data from database and write
        // the data to XLS file.one table one workbook.
        XlsDataSetWriter xds;
        xds = new XlsDataSetWriter();
        // export the data.
        xds.write(ds, os);
        LOG.info("export " + (ds.getTables().length - 1) + " tables");
        os.close();
        con.close();
    }
}
