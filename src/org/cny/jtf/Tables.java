package org.cny.jtf;

import org.dbunit.dataset.*;
import org.dbunit.dataset.datatype.DataType;

/**
 * The tables class
 *
 * @author Centny.
 */
public class Tables implements ITable {
    /**
     * the table row data.
     */
    private final String[] tables;
    /**
     * the table meta data.
     */
    private final ITableMetaData meta = new TablesMetaData();

    /**
     * the targets constructor.
     */
    public Tables(String targets) {
        if (null == targets || targets.trim().isEmpty()) {
            throw new IllegalArgumentException("the target is null or empty");
        }
        this.tables = targets.split(",");
    }

    @Override
    public int getRowCount() {
        return this.tables.length;
    }

    @Override
    public ITableMetaData getTableMetaData() {
        return this.meta;
    }

    @Override
    public Object getValue(int row, String column) throws DataSetException {
        if (row < 0 || row >= this.tables.length) {
            throw new RowOutOfBoundsException("the row is out of bounds:" + row);
        }
        if (null == column || !TablesMetaData.COLUMN_NAME.equals(column)) {
            throw new NoSuchColumnException("no the column:" + column);
        }
        return this.tables[row];
    }

    /**
     * The tables TableMetaData.
     *
     * @author Centny.
     */
    public class TablesMetaData implements ITableMetaData {
        /**
         * the table name.
         */
        public static final String TABLES_NAME = "TABLES";
        /**
         * the column name.
         */
        public static final String COLUMN_NAME = "NAME";
        /**
         * the columns.
         */
        private final Column column = new Column(COLUMN_NAME, DataType.VARCHAR,
                DataType.VARCHAR + "", Column.NO_NULLS);

        @Override
        public int getColumnIndex(String columnName) throws DataSetException {
            if (null == columnName || !COLUMN_NAME.equals(columnName)) {
                throw new NoSuchColumnException("no the column:" + columnName);
            }
            return 0;
        }

        @Override
        public Column[] getColumns() throws DataSetException {
            return new Column[]{column};
        }

        @Override
        public Column[] getPrimaryKeys() throws DataSetException {
            return new Column[]{column};
        }

        @Override
        public String getTableName() {
            return TABLES_NAME;
        }

    }
}
