package org.cny.jtf;

import java.sql.SQLException;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableIterator;
import org.dbunit.dataset.ITableMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The tables data set class
 *
 * @author Centny.
 */
public class TablesDataSet implements IDataSet {
    /**
     * the targets tables.
     */
    private final Tables tables;

    /**
     * the data set.
     */
    private final IDataSet ds;

    /**
     * the default constructor.
     *
     * @throws java.sql.SQLException
     * @throws DataSetException
     */
    public TablesDataSet(String targets, IDatabaseConnection dc)
            throws DataSetException, SQLException {
        this.tables = new Tables(targets);
        this.ds = dc.createDataSet(targets.split(","));
    }

    @Override
    public ITable getTable(String tableName) throws DataSetException {
        if (Tables.TablesMetaData.TABLES_NAME.equals(tableName)) {
            return this.tables;
        }
        return ds.getTable(tableName);
    }

    @Override
    public ITableMetaData getTableMetaData(String tableName)
            throws DataSetException {
        if (this.tables.getTableMetaData().getTableName().equals(tableName)) {
            return this.tables.getTableMetaData();
        }
        return ds.getTableMetaData(tableName);
    }

    @Override
    public String[] getTableNames() throws DataSetException {
        String[] otns = this.ds.getTableNames();
        String[] tns = new String[otns.length + 1];
        tns[0] = this.tables.getTableMetaData().getTableName();
        for (int i = 1; i < tns.length; i++) {
            tns[i] = otns[i - 1];
        }
        return tns;
    }

    @Override
    public ITable[] getTables() throws DataSetException {
        ITable[] ot = this.ds.getTables();
        ITable[] nt = new ITable[ot.length + 1];
        nt[0] = this.tables;
        for (int i = 1; i < nt.length; i++) {
            nt[i] = ot[i - 1];
        }
        return nt;
    }

    @Override
    public boolean isCaseSensitiveTableNames() {
        return this.ds.isCaseSensitiveTableNames();
    }

    @Override
    public ITableIterator iterator() throws DataSetException {
        return new TablesIterator(tables, this.ds.iterator());
    }

    @Override
    public ITableIterator reverseIterator() throws DataSetException {
        return new TablesIterator(tables, this.ds.reverseIterator());
    }

    /**
     * @author Centny.
     */
    public class TablesIterator implements ITableIterator {
        /**
         * the log.
         */
        private final Logger log = LoggerFactory.getLogger(this.getClass());
        /**
         * the targets table.
         */
        private final ITable tables;

        /**
         * whether the targets table have been iterated.
         */
        private int iteratTables = 0;

        /**
         * the iterator.
         */
        private final ITableIterator iterator;

        /**
         * @param tables the tables.
         */
        public TablesIterator(ITable tables, ITableIterator iterator) {
            this.tables = tables;
            this.iterator = iterator;
        }

        @Override
        public ITable getTable() throws DataSetException {
            switch (this.iteratTables) {
                case 0:
                    return null;
                case 1:
                    return this.tables;
                default:
                    this.log.info("exporting table:"
                            + this.iterator.getTable().getTableMetaData()
                            .getTableName());
                    return this.iterator.getTable();
            }
        }

        @Override
        public ITableMetaData getTableMetaData() throws DataSetException {
            switch (this.iteratTables) {
                case 0:
                    return null;
                case 1:
                    return this.tables.getTableMetaData();
                default:
                    return this.iterator.getTableMetaData();
            }
        }

        @Override
        public boolean next() throws DataSetException {
            switch (this.iteratTables) {
                case 0:
                    this.iteratTables++;
                    return true;
                case 1:
                    this.iteratTables++;
                    return this.iterator.next();
                default:
                    return this.iterator.next();
            }
        }
    }
}
