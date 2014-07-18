package org.cny.jtf.main;

import org.cny.jtf.DatabaseProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * the main class for export data for database.
 *
 * @author Scorpion.
 */
public class ExportData {
    /**
     * the log.
     */
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * the profile path.
     */
    private String profilePath;
    /**
     * the XLS file path.
     */
    private String xlsFilePath;

    /**
     * @param args the execute argument.
     * @throws Exception
     */
    public void execute(String[] args) throws Exception {
        if (args.length == 4) {
            for (int i = 0; i < 4; ) {
                if ("-pf".equals(args[i])) {
                    this.profilePath = args[++i];
                    i++;
                } else if ("-xf".equals(args[i])) {
                    this.xlsFilePath = args[++i];
                    i++;
                } else {
                    ExportData.help();
                }
            }
            if (this.profilePath == null || this.xlsFilePath == null) {
                ExportData.help();
            }
            this.log.info("the profile path:" + this.profilePath);
            this.log.info("the XLS file path:" + this.xlsFilePath);
            this.export();
        } else {
            ExportData.help();
        }
    }

    /**
     * export the data for the XLS file.
     *
     * @throws Exception
     */
    private void export() throws Exception {
        DatabaseProfile dp;
        InputStream input = null;
        OutputStream out = null;
        try {
            input = new FileInputStream(this.profilePath);
            out = new FileOutputStream(this.xlsFilePath);
            dp = new DatabaseProfile();
            dp.load(input);
            org.cny.jtf.ExportData.exportData(dp, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
                input = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
        }

    }

    /**
     * @param args the argument from command line.
     */
    public static void main(String[] args) {
        try {
            new ExportData().execute(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * print the help.
     */
    public static void help() {
        System.out.println("Please use:\n"
                + "\t-pf the profile configure file path.\n"
                + "\t-xf the XLS file path will be saved.");
        System.exit(0);
    }
}
