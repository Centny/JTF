package org.cny.test;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.cny.jtf.DatabaseProfile;
import org.junit.Test;

import static org.cny.jtf.ExportData.*;

/**
 * @author Centny.
 */
public class ExportTest {

    @Test
    public void testExport() throws Exception {
        String xls = System.getenv("TMPDIR");
        InputStream is;
        DatabaseProfile dp;
        FileOutputStream of;
        is = EtfTest.class.getResourceAsStream("profile.properties");
        dp = new DatabaseProfile().load(is);
        of = new FileOutputStream(xls + "DB.xls");
        exportData(dp, of);
        of.close();
        is = EtfTest.class.getResourceAsStream("profile2.properties");
        dp = new DatabaseProfile().load(is);
        of = new FileOutputStream(xls + "DB2.xls");
        exportData(dp, of);
        of.close();
    }
}
